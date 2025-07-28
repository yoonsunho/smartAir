package com.example.smartAir.controller;

import com.example.smartAir.dto.MapDataRequest;
import com.example.smartAir.service.MapDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/map-data")
public class MapDataController {

    private final MapDataService mapDataService;

    public MapDataController(MapDataService mapDataService) {
        this.mapDataService = mapDataService;
    }

    @PostMapping// 요청 바디 -> MapDataRequest DTO로 변환
    public ResponseEntity<String> receiveMapData(@RequestBody MapDataRequest request) {
        mapDataService.saveMapData(request); // service 호출
        return ResponseEntity.ok("Map data saved");
    }

    // 클라이언트에서 특정 장비 최신 지도 데이터 조회
    @GetMapping("/{orinId}/latest")
    public ResponseEntity<String> getLatestMapArray(@PathVariable String orinId) {
        String mapArrayJson = mapDataService.getLatestMapArrayByDevice(orinId);
        if (mapArrayJson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapArrayJson);
    }
}
