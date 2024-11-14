package com.singhtwenty2.OceanVista.service.location;

import com.singhtwenty2.OceanVista.data.model.dto.internal.BatchLocationInternalDTO;
import com.singhtwenty2.OceanVista.data.model.dto.internal.UserLocationInternal;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserLocationRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.UserLocation;
import com.singhtwenty2.OceanVista.data.model.entity.Users;
import com.singhtwenty2.OceanVista.data.repository.AuthRepository;
import com.singhtwenty2.OceanVista.data.repository.LocationRepository;
import com.singhtwenty2.OceanVista.util.auth.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthRepository authRepository;

    @Transactional
    public AppResponseDTO saveUserLocation(
            UserLocationRequestDTO requestDTO,
            String token
    ) {
        try {
            UserLocation location = new UserLocation();
            String email = jwtService.extractUserEmail(token);
            Users user = authRepository.findByEmail(email);
            location.setLatitude(requestDTO.getLatitude());
            location.setLongitude(requestDTO.getLongitude());
            location.setUser(user);
            locationRepository.save(location);
            return new AppResponseDTO("User's Location Updated", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponseDTO(null, "Failed To Save User Location");
        }
    }

    @Transactional
    public UserLocationInternal fetchUserLocation(
            String token
    ) {
        try {
            String email = jwtService.extractUserEmail(token);
            Users user = authRepository.findByEmail(email);
            UserLocation location = locationRepository.findAllByUserId(user.getId());
            if(location == null) {
                return new UserLocationInternal(
                        null,
                        "Failed To Fetch User's Location",
                        null
                );
            }
            return new UserLocationInternal(
                    "User's Location Fetched",
                    null,
                    location
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new UserLocationInternal(
                    null,
                    "Failed To Fetch User's Location",
                    null
            );
        }
    }

    @Transactional
    public AppResponseDTO updateUserLocation(
            String token,
            UserLocationRequestDTO request
    ) {
        try {
            String email = jwtService.extractUserEmail(token);
            Users user = authRepository.findByEmail(email);
            UserLocation location = locationRepository.findAllByUserId(user.getId());
            if (location == null) {
                return new AppResponseDTO(
                        null,
                        "No existing location found for the user. Please save the location first."
                );
            }
            location.setLatitude(request.getLatitude());
            location.setLongitude(request.getLongitude());
            locationRepository.save(location);
            return new AppResponseDTO(
                    "User's Location Updated Successfully",
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponseDTO(
                    null,
                    "Failed To Update User's Location"
            );
        }
    }

    @Transactional
    public void saveBatchUserLocations(List<BatchLocationInternalDTO> locations) {
        for(BatchLocationInternalDTO location: locations) {
            try {
                String email = jwtService.extractUserEmail(location.getToken());
                Users user = authRepository.findByEmail(email);
                UserLocation userLocation = locationRepository.findAllByUserId(user.getId());
                if (userLocation != null) {
                    userLocation.setLatitude(location.getLatitude());
                    userLocation.setLongitude(location.getLongitude());
                    locationRepository.save(userLocation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
