package com.singhtwenty2.OceanVista.controller.external;

import com.singhtwenty2.OceanVista.data.model.dto.internal.BatchLocationInternalDTO;
import com.singhtwenty2.OceanVista.data.model.dto.internal.UserLocationInternal;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserLocationRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.UserLocationResponseDTO;
import com.singhtwenty2.OceanVista.service.location.LocationProducer;
import com.singhtwenty2.OceanVista.service.location.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app.engine.api/v1/user/location/")
public class LocationController {

    @Autowired
    private LocationService service;
    @Autowired
    private LocationProducer locationProducer;

    @PostMapping
    public ResponseEntity<AppResponseDTO> saveLocation(
            @RequestBody UserLocationRequestDTO requestDTO,
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AppResponseDTO(null, "Failed To Authenticate User.")
            );
        }
        AppResponseDTO response = service.saveUserLocation(requestDTO, token);
        if (response.getSuccessMessage() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AppResponseDTO(null, "Failed To Save User's Location.")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new AppResponseDTO("User's Location Saved.", null)
        );
    }

    @GetMapping
    public ResponseEntity<UserLocationResponseDTO> getUserLocation(
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new UserLocationResponseDTO(
                            0.0,
                            0.0
                    ));
        }
        UserLocationInternal response = service.fetchUserLocation(token);
        if(response.getSuccessMessage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new UserLocationResponseDTO(
                            0.0,
                            0.0
                    ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new UserLocationResponseDTO(
                       response.getLocationResponse().getLatitude(),
                       response.getLocationResponse().getLongitude()
                ));
    }

    @PutMapping
    public ResponseEntity<AppResponseDTO> updateUserLocation(
            HttpServletRequest request,
            @RequestBody UserLocationRequestDTO requestDTO
    ) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if(token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AppResponseDTO(
                            null,
                            "Failed To Update User's Location"
                    ));
        }
        try {
            BatchLocationInternalDTO batchLocationInternal = new BatchLocationInternalDTO();
            batchLocationInternal.setToken(token);
            batchLocationInternal.setLatitude(requestDTO.getLatitude());
            batchLocationInternal.setLongitude(requestDTO.getLongitude());
            locationProducer.sendLocationUpdate(batchLocationInternal);
            return ResponseEntity.ok(
                    new AppResponseDTO("User's Location Has Been Enqueued for Update.", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AppResponseDTO(null, "Failed To Process The Request. Please Try Again.")
            );
        }
    }
}
