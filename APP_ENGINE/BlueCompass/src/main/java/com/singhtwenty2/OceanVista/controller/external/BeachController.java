package com.singhtwenty2.OceanVista.controller.external;

import com.singhtwenty2.OceanVista.data.model.dto.request.BeachRequestDTO;
import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import com.singhtwenty2.OceanVista.service.beach.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app.engine.api/v1/core/beach/")
public class BeachController {

    private final BeachService beachService;

    public BeachController(BeachService beachService) {
        this.beachService = beachService;
    }

    @PostMapping
    public ResponseEntity<Beach> saveBeachDetails(@RequestBody BeachRequestDTO beachRequestDTO) {
        Beach savedBeach = beachService.saveBeach(beachRequestDTO);
        if(savedBeach == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedBeach, HttpStatus.CREATED);
    }
}
