package com.singhtwenty2.OceanVistaBusiness.service.auth;

import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.LoginRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.PartnerEmailRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.RegisterPartnerRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.LoginResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.PartnerDetailResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.Partner;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.SubscriptionPlan;
import com.singhtwenty2.OceanVistaBusiness.data.model.enums.PartnerStatus;
import com.singhtwenty2.OceanVistaBusiness.data.repository.auth.AuthRepository;
import com.singhtwenty2.OceanVistaBusiness.data.repository.plans.SubscriptionPlanRepository;
import com.singhtwenty2.OceanVistaBusiness.service.email.EmailService;
import com.singhtwenty2.OceanVistaBusiness.service.redis.RedisService;
import com.singhtwenty2.OceanVistaBusiness.util.auth.ValidationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final AuthRepository authRepository;
    private final RedisService redisService;
    private final EmailService emailService;
    private final JwtService jwtService;
    @Value("${connection_string.url}")
    private String connectionUrl;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AuthService(AuthRepository authRepository, RedisService redisService, EmailService emailService, JwtService jwtService, SubscriptionPlanRepository subscriptionPlanRepository) {
        this.authRepository = authRepository;
        this.redisService = redisService;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public AppResponseDTO checkUserEmail(PartnerEmailRequestDTO requestDTO) {
        Partner partner = authRepository.findByEmail(requestDTO.getEmail());
        if (partner != null) {
            return new AppResponseDTO(null, "Email Already Linked With An Account");
        }
        return new AppResponseDTO("Account Can Be Created Using This Email", null);
    }

    public AppResponseDTO registerUser(RegisterPartnerRequestDTO requestDTO) {
        String verificationToken = ValidationToken.generateVerificationToken();
        redisService.savePartnerToken(
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
        RegisterPartnerRequestDTO partnerDetails = redisService.getPartnerDetailsByToken(token);
        if(partnerDetails == null) {
            return new AppResponseDTO(null, "Failed To Verify Email");
        }
        savePartnerDetails(partnerDetails);
        redisService.deletePartnerToken(token);
        return new AppResponseDTO("Email Verified & Account Registration Completed", null);
    }

    private void savePartnerDetails(RegisterPartnerRequestDTO partnerDetails) {
        Partner partner = new Partner();
        partner.setName(partnerDetails.getName());
        partner.setEmail(partnerDetails.getEmail());
        partner.setPassword(encoder.encode(partnerDetails.getPassword()));
        partner.setPhoneNumber(partnerDetails.getPhoneNumber());
        partner.setPartnerType(partnerDetails.getPartnerType());
        partner.setStatus(PartnerStatus.ACTIVE);
        partner.setMaxBeachCount(1);
        SubscriptionPlan starterPlan = subscriptionPlanRepository.findByName("Starter");
        partner.setSubscriptionPlan(starterPlan);
        authRepository.save(partner);
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

    public PartnerDetailResponseDTO getPartnerDetails(String token) {
        String email = jwtService.extractUserEmail(token);
        Partner partner = authRepository.findByEmail(email);
        if(partner != null) {
            return new PartnerDetailResponseDTO(
                    partner.getId().toString(),
                    partner.getSubscriptionPlan().getName(),
                    partner.getName(),
                    partner.getEmail(),
                    partner.getPhoneNumber(),
                    partner.getPartnerType().name(),
                    partner.getStatus().name(),
                    partner.getMaxBeachCount(),
                    partner.getCreatedAt(),
                    partner.getUpdatedAt()
            );
        }
        return null;
    }
}