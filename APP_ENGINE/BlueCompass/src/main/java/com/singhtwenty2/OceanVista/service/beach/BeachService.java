package com.singhtwenty2.OceanVista.service.beach;

import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import com.singhtwenty2.OceanVista.data.model.entity.BeachDetail;
import com.singhtwenty2.OceanVista.data.repository.BeachRepository;
import org.springframework.stereotype.Service;

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
}
