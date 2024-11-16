package com.singhtwenty2.OceanVista.controller.external;

import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.BeachResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import com.singhtwenty2.OceanVista.service.beach.BeachService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app.engine.api/v1/core/beach/")
public class BeachController {

    private final BeachService beachService;

    public BeachController(BeachService beachService) {
        this.beachService = beachService;
    }

    // TODO : Make this Internal Controller
    @PostMapping
    public ResponseEntity<Beach> saveBeachDetails(
            @RequestBody BeachRequestDTO beachRequestDTO
    ) {
        Beach savedBeach = beachService.saveBeach(beachRequestDTO);
        if(savedBeach == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedBeach, HttpStatus.CREATED);
    }

    // TODO: Paginated Or Cache
    @GetMapping
    public ResponseEntity<List<BeachResponseDTO>> getBeaches(
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        List<BeachResponseDTO> beachResponseDTOS = beachService.getBeaches();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beachResponseDTOS);
    }

    // TODO: Cache
    @GetMapping("/{beachId}")
    public ResponseEntity<BeachResponseDTO> getBeachById(
            HttpServletRequest request,
            @PathVariable Long beachId
    ) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        BeachResponseDTO beachResponseDTO = beachService.getBeachById(beachId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beachResponseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BeachResponseDTO>> searchBeaches(
            HttpServletRequest request,
            @RequestParam String name
    ) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        List<BeachResponseDTO> beachResponseDTOS = beachService.searchBeaches(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beachResponseDTOS);
    }

    // TODO: Cache
    @GetMapping("/about/{beachId}")
    public ResponseEntity<Beach> getBeachDetailById(
            HttpServletRequest request,
            @PathVariable Long beachId
    ) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        Beach beach = beachService.getBeachDetailById(beachId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beach);
    }

    @DeleteMapping("/{beachId}")
    public ResponseEntity<AppResponseDTO> deleteBeach(
            HttpServletRequest request,
            @PathVariable Long beachId
    ) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        AppResponseDTO appResponseDTO = beachService.deleteBeach(beachId);
        if(appResponseDTO.getSuccessMessage() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(appResponseDTO);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appResponseDTO);
    }

}
