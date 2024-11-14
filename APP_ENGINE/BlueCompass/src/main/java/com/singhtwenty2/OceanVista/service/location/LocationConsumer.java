package com.singhtwenty2.OceanVista.service.location;

import com.singhtwenty2.OceanVista.data.model.dto.internal.BatchLocationInternalDTO;
import com.singhtwenty2.OceanVista.data.model.dto.request.UserLocationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationConsumer {

    private final LocationService locationService;
    @Value("${location_batch_kafka.batch_size}")
    private int batchSize;

    private final List<BatchLocationInternalDTO> batch = new ArrayList<>();

    @Autowired
    public LocationConsumer(LocationService locationService) {
        this.locationService = locationService;
    }

    @KafkaListener(topics = "user-location-topic", containerFactory = "kafkaListenerContainerFactory")
    public void batchLocationUpdate(BatchLocationInternalDTO location) {
        batch.add(location);

        if (batch.size() >= batchSize) {
            try {
                locationService.saveBatchUserLocations(new ArrayList<>(batch));
                batch.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
