package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import com.builder.demo.model.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
        Room room = roomRepository.findByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(buildingId, floorId, roomId).get();
        return new Stats(room);
    }

    public Stats getFloorStats(Long buildingId, Long floorId) {
        Floor floor = floorRepository.findByBuildingIdEqualsAndFloorIdEquals(buildingId, floorId).get();
        return new Stats(floor);
    }

    public Stats getBuildingStats(Long buildingId) {
        Building building = buildingRepository.findById(buildingId).get();
        return new Stats(building);
    }
}
