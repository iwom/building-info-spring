package com.builder.demo.service.impl;

import com.builder.demo.service.RoomService;
import com.builder.demo.shared.dto.RoomDto;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Override
    public RoomDto createRoom(RoomDto roomDto, Long buildingId, Long floorId) {
        return null;
    }
}
