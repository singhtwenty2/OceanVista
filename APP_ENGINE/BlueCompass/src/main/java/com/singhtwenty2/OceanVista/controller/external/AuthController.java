package com.singhtwenty2.OceanVista.controller.external;

import com.singhtwenty2.OceanVista.data.model.dto.request.LoginRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.RegisterRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserEmailRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.LoginResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.UserDetailResponseDTO;
import com.singhtwenty2.OceanVista.service.auth.AuthService;
import com.singhtwenty2.OceanVista.util.template.WebPage;
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
@RequestMapping("/app.engine.api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/email-check")
    public ResponseEntity<AppResponseDTO> checkUserEmail(
            @RequestBody UserEmailRequestDTO requestDTO
    ) {
        AppResponseDTO response = service.checkUserEmail(requestDTO);

        if (response.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AppResponseDTO> register(
            @RequestBody RegisterRequestDTO requestDTO
    ) {
        AppResponseDTO response = service.registerUser(requestDTO);
        if (response.getSuccessMessage() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        System.out.println("*****\n\n\nEmail Verication Triggered****");
        AppResponseDTO response = service.verifyUserEmail(token);
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
        LoginResponseDTO loginResponseDTO = service.login(requestDTO);
        if(loginResponseDTO.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponseDTO);
        }
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping("/about")
    public ResponseEntity<UserDetailResponseDTO> getUserDetail(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if(token == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDetailResponseDTO());
        }
        UserDetailResponseDTO response = service.getUserDetails(token);
        if(response == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDetailResponseDTO());
        }
        System.out.println("\n\n\n*****About Profile Triggered****");
        return ResponseEntity.ok(response);
    }
}