package com.builder.demo.service.impl;

import com.builder.demo.model.Room;
import com.builder.demo.model.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.StatsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    ModelMapper modelMapper;

    public StatsServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    public Stats getRoomStats(Long buildingId, Long floorId, Long roomId) {
        List<Room> roomList = roomRepository.findAllByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(buildingId, floorId, roomId);
        return new Stats(roomList);
    }

    public Stats getFloorStats(Long buildingId, Long floorId) {
        List<Room> roomList = roomRepository.findAllByBuildingIdEqualsAndFloorFloorIdEquals(buildingId, floorId);
        return new Stats(roomList);
    }

    public Stats getBuildingStats(Long buildingId) {
        List<Room> roomList = roomRepository.findAllByBuildingIdEquals(buildingId);
        return new Stats(roomList);
    }
}
