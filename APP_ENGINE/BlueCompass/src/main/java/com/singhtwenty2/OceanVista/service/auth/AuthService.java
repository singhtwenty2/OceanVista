package com.singhtwenty2.OceanVista.service.auth;

import com.singhtwenty2.OceanVista.data.model.dto.request.LoginRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.RegisterRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserEmailRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.LoginResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.UserDetailResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Users;
import com.singhtwenty2.OceanVista.data.model.enums.AuthProvider;
import com.singhtwenty2.OceanVista.data.repository.AuthRepository;
import com.singhtwenty2.OceanVista.service.email.EmailService;
import com.singhtwenty2.OceanVista.service.redis.RedisService;
import com.singhtwenty2.OceanVista.util.auth.ExtractUsername;
import com.singhtwenty2.OceanVista.util.auth.JwtService;
import com.singhtwenty2.OceanVista.util.auth.ValidationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Value("${connection_string.url}")
    private String connectionUrl;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AppResponseDTO checkUserEmail(UserEmailRequestDTO requestDTO) {
        Users user = repository.findByEmail(requestDTO.getEmail());
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
        saveUser(userDetails);
        redisService.deleteUserToken(token);
        return new AppResponseDTO("Email Verified & Account Registration Completed", null);
    }

    private void saveUser(RegisterRequestDTO userDetails) {
        String username = ExtractUsername.extractUsernameFromEmail(userDetails.getEmail());
        Users user = new Users();
        user.setUsername(username);
        user.setName(userDetails.getName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setEmail(userDetails.getEmail());
        user.setPassword(encoder.encode(userDetails.getPassword()));
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setNotificationPreference(userDetails.getNotificationPreference());
        repository.save(user);
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
        Users user = repository.findByEmail(email);
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
