package com.example.smartAir.service;

import com.example.smartAir.domain.Device;
import com.example.smartAir.domain.MapData;
import com.example.smartAir.dto.MapDataRequest;
import com.example.smartAir.repository.DeviceRepository;
import com.example.smartAir.repository.MapDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service // 의존성 주입의 대상
public class MapDataService {

    private final MapDataRepository mapDataRepository;
    private final DeviceRepository deviceRepository;

    public MapDataService(MapDataRepository mapDataRepository, DeviceRepository deviceRepository) {
        this.mapDataRepository = mapDataRepository;
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public void saveMapData(MapDataRequest request){
        // 1. orinId 값으로 device 앤티티 검색. 없으면 새 device 객체 만들어 저장
        Device device = deviceRepository.findById(request.getOrinId())
                .orElseGet(() -> deviceRepository.save(new Device(request.getOrinId())));

        // 2. 측정 일시 문자열(ISO8601 형식)을 LocalDateTime 객체로 변환
        LocalDateTime measuredAt = LocalDateTime.parse(request.getMeasuredAt());

        // 3. 위의 정보 바탕으로 MapData 앤티티 객체 생성
        MapData mapData = new MapData(device,measuredAt, request.getMapArray());

        // 4. db에 MapData 저장(insert쿼리 실행)
        mapDataRepository.save(mapData);
    }

    // 오린카의 가장 최신 지도 JSON 데이터를 조회하는 메서드
    public String getLatestMapArrayByDevice(String orinId){
        return mapDataRepository.findToByDeviceOrinIdOrderByMeasuredAtDesc(orinId)
                .map(MapData::getMapArray) // MapData 객체에서 getMapArray() 메서드를 호출해 mapArray 꺼내기 
                .orElse(null);
    }
}

