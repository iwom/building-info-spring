package com.builder.demo.service;

import com.builder.demo.shared.dto.FloorDto;

public interface FloorService {
    FloorDto createFloor(FloorDto floorDto, Long buildingId);
}
