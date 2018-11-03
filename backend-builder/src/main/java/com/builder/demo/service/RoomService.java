package com.builder.demo.service;

import com.builder.demo.shared.dto.RoomDto;

public interface RoomService {
    RoomDto createRoom(RoomDto roomDto, Long buildingId, Long floorId);
}
