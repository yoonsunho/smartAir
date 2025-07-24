package com.example.smartAir.service;

import com.example.smartAir.domain.Device;
import com.example.smartAir.domain.SensorData;
import com.example.smartAir.dto.SensorDataRequest;
import com.example.smartAir.repository.DeviceRepository;
import com.example.smartAir.repository.SensorDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Service  // Service 레이어 빈 등록
public class SensorDataService {

    private final DeviceRepository deviceRepository;
    private final SensorDataRepository sensorDataRepository;

    // 의존성 주입
    public SensorDataService(DeviceRepository deviceRepository, SensorDataRepository sensorDataRepository) {
        this.deviceRepository = deviceRepository;
        this.sensorDataRepository = sensorDataRepository;
    }

    @Transactional
    public SensorData saveSensorData(SensorDataRequest request){
        // device 존재 유무 확인
        Device device = deviceRepository.findById(request.getOrinId())
                .orElseGet(() -> deviceRepository.save(new Device(request.getOrinId())));

        // measuredAt parsing
        LocalDateTime measuredAt;
        try{
            measuredAt = LocalDateTime.parse(request.getMeasuredAt());
        }catch (DateTimeParseException e){
            measuredAt = LocalDateTime.now();
        }

        SensorData sensorData = new SensorData(
                device,
                measuredAt,
                request.getTemperature(),
                request.getHumidity(),
                request.getAirQuality(),
                request.getCoordX(),
                request.getCoordY(),
                request.getRoom()
        );

        return sensorDataRepository.save(sensorData);
    }


}
