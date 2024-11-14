package com.singhtwenty2.OceanVista.service.location;

import com.singhtwenty2.OceanVista.data.model.dto.internal.BatchLocationInternalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationProducer {

    private final KafkaTemplate<String, BatchLocationInternalDTO> kafkaTemplate;

    @Autowired
    public LocationProducer(KafkaTemplate<String, BatchLocationInternalDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLocationUpdate(BatchLocationInternalDTO batchLocationInternal) {
        kafkaTemplate.send("user-location-topic", batchLocationInternal);
    }
}

