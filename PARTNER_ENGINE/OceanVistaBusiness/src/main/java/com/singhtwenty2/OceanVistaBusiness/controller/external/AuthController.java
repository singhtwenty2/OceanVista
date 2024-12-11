package com.singhtwenty2.OceanVistaBusiness.controller.external;

import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.LoginRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.PartnerEmailRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.RegisterPartnerRequestDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.LoginResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.PartnerDetailResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.Partner;
import com.singhtwenty2.OceanVistaBusiness.service.auth.AuthService;
import com.singhtwenty2.OceanVistaBusiness.util.template.WebPage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/partner.engine.api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/email-check")
    public ResponseEntity<AppResponseDTO> cheakEmail(
            @RequestBody PartnerEmailRequestDTO requestDTO
    ) {
        AppResponseDTO response = authService.checkUserEmail(requestDTO);

        if (response.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AppResponseDTO> registerUser(
            @RequestBody RegisterPartnerRequestDTO requestDTO
    ) {
        System.out.println("\n\n\n*****Signup Triggered****");
        AppResponseDTO response = authService.registerUser(requestDTO);
        if (response.getSuccessMessage() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        System.out.println("*****\n\n\nEmail Verification Triggered****");
        AppResponseDTO response = authService.verifyUserEmail(token);
        String html = WebPage.EmailVerificationPage(response.getSuccessMessage() != null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return ResponseEntity
                .status(response.getSuccessMessage() != null ? HttpStatus.OK : HttpStatus.UNAUTHORIZED)
                .headers(headers)
                .body(html);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO requestDTO
    ) {
        System.out.println("\n\n\n*****Login Triggered****");
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                requestDTO.getEmail(),
                                requestDTO.getPassword()
                        ));
        if(!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LoginResponseDTO(null, null, "Failed To Authenticate User")
            );
        }
        LoginResponseDTO loginResponseDTO = authService.login(requestDTO);
        if(loginResponseDTO.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponseDTO);
        }
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping("/about")
    public ResponseEntity<PartnerDetailResponseDTO> getUserDetail(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if(token == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new PartnerDetailResponseDTO());
        }
        PartnerDetailResponseDTO response = authService.getPartnerDetails(token);
        if(response == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new PartnerDetailResponseDTO());
        }
        return ResponseEntity.ok(response);
    }
}
