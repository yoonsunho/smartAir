package com.example.smartAir.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MapData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orin_id")
    private Device device;

    private LocalDateTime measuredAt;

    @Column(name = "map_array", columnDefinition = "json") //Postgresql json 타입
    private String mapArray;    // 2D 배열 JSON 문자열로 저장

    public MapData(Device device, LocalDateTime measuredAt, String mapArray) {
        this.device = device;
        this.measuredAt = measuredAt;
        this.mapArray = mapArray;
    }
}
