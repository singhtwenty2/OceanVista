package com.singhtwenty2.OceanVista.service.beach;

import com.singhtwenty2.OceanVista.data.model.dto.response.AllBeachResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVista.data.model.dto.response.BeachResponseDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import com.singhtwenty2.OceanVista.data.repository.BeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeachService {

    @Autowired
    private BeachRepository beachRepository;

    public AppResponseDTO saveBeachDetails(
            BeachRequestDTO request
    ) {
        try {
            Beach beach = new Beach();
            beach.setName(request.getName());
            beach.setLatitude(request.getLatitude());
            beach.setLongitude(request.getLongitude());
            beach.setRegion(request.getRegion());
            beach.setDescription(request.getDescription());
            beachRepository.save(beach);
            return new AppResponseDTO(
                    "Beach Details Saved Successfully",
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponseDTO(
                    null,
                    "Failed To Save Beach Details"
            );
        }
    }

    public AllBeachResponseDTO getAllBeach() {
        List<Beach> beaches = beachRepository.findAll();
        if (beaches.isEmpty()) {
            return new AllBeachResponseDTO(
                    null,
                    null,
                    "Can't Find Beaches."
            );
        }
        return new AllBeachResponseDTO(
                "Beaches Details Fetched Successfully",
                beaches,
                null
        );
    }

    public BeachResponseDTO getBeachDetailsById(String beachId) {
        UUID beachIdAsUUID = UUID.fromString(beachId);
        try {
            Beach beach = beachRepository.getReferenceById(beachIdAsUUID);
            if (beach == null) {
                return new BeachResponseDTO(
                        null,
                        null,
                        "No Beach Found For The Given Id."
                );
            }
            return new BeachResponseDTO(
                    "Beach Found.",
                    beach,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new BeachResponseDTO(
                    null,
                    null,
                    "Failed To Fetch Beache."
            );
        }
    }
}
