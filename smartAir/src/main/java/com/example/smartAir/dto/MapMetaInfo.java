package com.example.smartAir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapMetaInfo {

    private Float resolution;      // 해상도
    private Float originX;         // 원점 X 좌표
    private Float originY;         // 원점 Y 좌표
    private Float originTheta;     // 회전 각도
    private Float occupiedThresh;  // 점유 임계값
    private Float freeThresh;      // 자유 공간 임계값
    private Boolean negate;        // 색상 반전 여부

}
