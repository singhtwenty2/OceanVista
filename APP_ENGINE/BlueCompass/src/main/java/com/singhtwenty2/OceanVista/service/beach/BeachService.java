package com.singhtwenty2.OceanVista.service.beach;

import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.BeachResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import com.singhtwenty2.OceanVista.data.model.entity.BeachDetail;
import com.singhtwenty2.OceanVista.data.repository.BeachRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BeachService {

    private final BeachRepository beachRepository;
    public BeachService(BeachRepository beachRepository) {
        this.beachRepository = beachRepository;
    }

    public Beach saveBeach(BeachRequestDTO dto) {
        BeachDetail beachDetail = new BeachDetail();
        beachDetail.setActivities(dto.getActivities());
        beachDetail.setAmenities(dto.getAmenities());
        beachDetail.setOpeningTime(dto.getOpeningTime());
        beachDetail.setClosingTime(dto.getClosingTime());
        beachDetail.setAverageCrowdCount(dto.getAverageCrowdCount());
        beachDetail.setPeakTimes(dto.getPeakTimes());
        beachDetail.setSeasonalActivities(dto.getSeasonalActivities());
        beachDetail.setFacilities(dto.getFacilities());
        beachDetail.setServices(dto.getServices());
        beachDetail.setAccessibleForDisabled(dto.isAccessibleForDisabled());
        beachDetail.setWeatherForecast(dto.getWeatherForecast());
        beachDetail.setPetFriendly(dto.isPetFriendly());
        beachDetail.setFoodAndBeverageOptions(dto.getFoodAndBeverageOptions());
        beachDetail.setShadeAvailable(dto.isShadeAvailable());
        beachDetail.setParkingInfo(dto.getParkingInfo());
        beachDetail.setSurfConditions(dto.getSurfConditions());
        beachDetail.setLifeguardsAvailable(dto.isLifeguardsAvailable());
        beachDetail.setFamilyFriendly(dto.isFamilyFriendly());
        beachDetail.setWifiAvailable(dto.isWifiAvailable());
        beachDetail.setBeachType(dto.getBeachType());
        beachDetail.setRulesAndRegulations(dto.getRulesAndRegulations());
        beachDetail.setEmergencyContactInfo(dto.getEmergencyContactInfo());

        Beach beach = new Beach();
        beach.setName(dto.getName());
        beach.setLatitude(dto.getLatitude());
        beach.setLongitude(dto.getLongitude());
        beach.setRegion(dto.getRegion());
        beach.setDescription(dto.getDescription());
        beach.setPhotos(dto.getPhotos());
        beach.setBeachDetail(beachDetail);
        return beachRepository.save(beach);
    }

    public List<BeachResponseDTO> getBeaches() {
        try {
            List<Beach> beaches = beachRepository.findAll();
            return beaches.stream()
                    .map(beach -> new BeachResponseDTO(
                            beach.getId(),
                            beach.getName(),
                            beach.getLatitude(),
                            beach.getLongitude(),
                            beach.getRegion(),
                            beach.getBeachDetail().getOpeningTime(),
                            beach.getBeachDetail().getClosingTime(),
                            beach.getBeachDetail().getActivities(),
                            beach.getPhotos()
                    ))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BeachResponseDTO getBeachById(Long beachId) {
        try {
            Beach beach = beachRepository.findById(beachId).orElse(null);
            if (beach == null) {
                return null;
            }
            return new BeachResponseDTO(
                    beach.getId(),
                    beach.getName(),
                    beach.getLatitude(),
                    beach.getLongitude(),
                    beach.getRegion(),
                    beach.getBeachDetail().getOpeningTime(),
                    beach.getBeachDetail().getClosingTime(),
                    beach.getBeachDetail().getActivities(),
                    beach.getPhotos()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Beach getBeachDetailById(Long beachId) {
        try {
            return beachRepository.findById(beachId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AppResponseDTO deleteBeach(Long beachId) {
        try {
            beachRepository.deleteById(beachId);
            return new AppResponseDTO("Beach deleted successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponseDTO(null, "Failed to delete beach");
        }
    }

    public List<BeachResponseDTO> searchBeaches(String query) {
        try {
            List<Beach> beachesByName = beachRepository.findByNameContainingIgnoreCase(query);
            List<Beach> beachesByRegion = beachRepository.findByRegionContainingIgnoreCase(query);
            List<Beach> combinedBeaches = Stream.concat(beachesByName.stream(), beachesByRegion.stream())
                    .distinct()
                    .collect(Collectors.toList());
            return combinedBeaches.stream()
                    .map(beach -> new BeachResponseDTO(
                            beach.getId(),
                            beach.getName(),
                            beach.getLatitude(),
                            beach.getLongitude(),
                            beach.getRegion(),
                            beach.getBeachDetail().getOpeningTime(),
                            beach.getBeachDetail().getClosingTime(),
                            beach.getBeachDetail().getActivities(),
                            beach.getPhotos()
                    ))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
