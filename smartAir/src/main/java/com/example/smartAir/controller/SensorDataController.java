package com.example.smartAir.controller;

import com.example.smartAir.domain.SensorData;
import com.example.smartAir.dto.SensorDataRequest;
import com.example.smartAir.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping
    public ResponseEntity<String> receiveSensorData(@Valid @RequestBody SensorDataRequest request){
        SensorData saved = sensorDataService.saveSensorData(request);
        return ResponseEntity.ok("Data saved with id" + saved.getDataId());

    }
}
