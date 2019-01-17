package com.builder.demo.service;

import com.builder.demo.model.impl.Stats;

public interface StatsService {
    Stats getBuildingStats(Long buildingId);
    Stats getFloorStats(Long buildingId, Long floorId);
    Stats getRoomStats(Long buildingId, Long floorId, Long roomId);
}
