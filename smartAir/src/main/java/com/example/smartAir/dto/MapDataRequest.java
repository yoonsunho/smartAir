package com.example.smartAir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapDataRequest {
    private String orinId;
    private String measuredAt;  // ISO 8601 형식 문자열, 예: "2025-07-24T15:00:00"
    private String mapArray;
    // 2D 배열 JSON 문자열 (예: "[[0,1,0],[1,0,1]]")


}
