package com.builder.demo.service.impl;

import com.builder.demo.service.FloorService;
import com.builder.demo.shared.dto.FloorDto;
import org.springframework.stereotype.Service;

@Service
public class FloorServiceImpl implements FloorService {
    @Override
    public FloorDto createFloor(FloorDto floorDto, Long buildingId) {
        return null;
    }
}
