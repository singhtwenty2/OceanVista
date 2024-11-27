package com.singhtwenty2.OceanVista.service.auth;

import com.singhtwenty2.OceanVista.data.model.dto.request.LoginRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.RegisterRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserEmailRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.LoginResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.UserDetailResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.UserLocation;
import com.singhtwenty2.OceanVista.data.model.entity.Users;
import com.singhtwenty2.OceanVista.data.model.enums.AuthProvider;
import com.singhtwenty2.OceanVista.data.repository.AuthRepository;
import com.singhtwenty2.OceanVista.data.repository.LocationRepository;
import com.singhtwenty2.OceanVista.service.email.EmailService;
import com.singhtwenty2.OceanVista.service.redis.RedisService;
import com.singhtwenty2.OceanVista.util.auth.ExtractUsername;
import com.singhtwenty2.OceanVista.util.auth.JwtService;
import com.singhtwenty2.OceanVista.util.auth.ValidationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final RedisService redisService;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final LocationRepository locationRepository;
    @Value("${connection_string.url}")
    private String connectionUrl;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AuthService(AuthRepository authRepository,
                       RedisService redisService,
                       EmailService emailService,
                       JwtService jwtService,
                       LocationRepository locationRepository) {
        this.authRepository = authRepository;
        this.redisService = redisService;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.locationRepository = locationRepository;
    }

    public AppResponseDTO checkUserEmail(UserEmailRequestDTO requestDTO) {
        Users user = authRepository.findByEmail(requestDTO.getEmail());
        if (user != null) {
            return new AppResponseDTO(null, "Email Already Linked With An Account");
        }
        return new AppResponseDTO("Account Can Be Created Using This Email", null);
    }

    public AppResponseDTO registerUser(RegisterRequestDTO requestDTO) {
        String verificationToken = ValidationToken.generateVerificationToken();
        redisService.saveUserToken(
                verificationToken,
                requestDTO
        );
        String verificationLink = connectionUrl + verificationToken;
        CompletableFuture<Boolean> isEmailSent = emailService.sendVerificationEmail(
                "mrysg.test@gmail.com",
                requestDTO.getEmail(),
                verificationLink
        );
        if (isEmailSent.join()) {
            return new AppResponseDTO("Verification Email Sent", null);
        }
        return new AppResponseDTO(null, "Failed To Send Verification Email");
    }

    public AppResponseDTO verifyUserEmail(String token) {
        RegisterRequestDTO userDetails = redisService.getUserDetailsByToken(token);
        if(userDetails == null) {
            return new AppResponseDTO(null, "Failed To Verify Email");
        }
        saveUserAndLocation(userDetails);
        redisService.deleteUserToken(token);
        return new AppResponseDTO("Email Verified & Account Registration Completed", null);
    }

    private void saveUserAndLocation(RegisterRequestDTO userDetails) {
        String username = ExtractUsername.extractUsernameFromEmail(userDetails.getEmail());
        Users user = new Users();
        user.setUsername(username);
        user.setName(userDetails.getName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setEmail(userDetails.getEmail());
        user.setPassword(encoder.encode(userDetails.getPassword()));
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setNotificationPreference(userDetails.getNotificationPreference());
        Users savedUser = authRepository.save(user);

        UserLocation dummyLocation = new UserLocation();
        dummyLocation.setLatitude(0.0);
        dummyLocation.setLongitude(0.0);
        dummyLocation.setUser(savedUser);
        locationRepository.save(dummyLocation);
    }


    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        try {
            String token = jwtService.generateJWT(requestDTO.getEmail());
            return new LoginResponseDTO(
                    "Login Request Was Successful",
                    token,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDTO(null, null, "Failed To Generate Token");
        }
    }

    public UserDetailResponseDTO getUserDetails(String token) {
        String email = jwtService.extractUserEmail(token);
        Users user = authRepository.findByEmail(email);
        if(user != null) {
            return new UserDetailResponseDTO(
                    user.getId().toString(),
                    user.getUsername(),
                    user.getName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getNotificationPreference().name()
            );
        }
        return null;
    }
}
