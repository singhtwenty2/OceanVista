package com.singhtwenty2.OceanVista.controller.external;

import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AllBeachResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.BeachResponseDTO;
import com.singhtwenty2.OceanVista.service.beach.BeachService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app.engine.api/v1/core/beach/")
public class BeachController {

    @Autowired
    private BeachService service;

    @PostMapping
    public ResponseEntity<AppResponseDTO> saveBeach(
            HttpServletRequest request,
            @RequestBody BeachRequestDTO requestDTO
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AppResponseDTO(null, "Failed To Authenticate User.")
            );
        }
        AppResponseDTO response = service.saveBeachDetails(requestDTO);
        if (response.getSuccessMessage() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AppResponseDTO(
                            null,
                            "Failed To Save Beach Details."
                    ));
        }
        return ResponseEntity.ok(
                new AppResponseDTO(
                        response.getSuccessMessage(),
                        null
                ));
    }

    @GetMapping
    public ResponseEntity<AllBeachResponseDTO> getAllBeach(
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AllBeachResponseDTO(null, null, "Failed To Authenticate User.")
            );
        }
        AllBeachResponseDTO response = service.getAllBeach();
        if (response.getSuccessMessage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new AllBeachResponseDTO(
                            null,
                            null,
                            response.getErrorMessage()
                    ));
        }
        return ResponseEntity.ok(
                new AllBeachResponseDTO(
                        response.getSuccessMessage(),
                        response.getBeach(),
                        null
                ));
    }

    @GetMapping("/id")
    public ResponseEntity<BeachResponseDTO> getBeachById(
            HttpServletRequest request,
            @RequestParam String beachId
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new BeachResponseDTO(null,
                            null,
                            "Failed To Authenticate User."
                    ));
        }
        BeachResponseDTO response = service.getBeachDetailsById(beachId);
        if (response.getSuccessMessage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new BeachResponseDTO(
                            null,
                            null,
                            response.getErrorMessage()
                    ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new BeachResponseDTO(
                        response.getSuccessMessage(),
                        response.getBeach(),
                        null
                ));
    }
}
