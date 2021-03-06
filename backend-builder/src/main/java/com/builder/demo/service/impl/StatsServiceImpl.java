package com.builder.demo.service.impl;

import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.model.impl.Room;
import com.builder.demo.model.impl.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        Stats returnStats = new Stats(room);
        log.info("Room stats successfully fetched");
        log.info(returnStats.toString());
        return returnStats;
    }

    public Stats getFloorStats(Long buildingId, Long floorId) {
        Floor floor = floorRepository.findByBuildingIdEqualsAndFloorIdEquals(buildingId, floorId).get();
        Stats returnStats = new Stats(floor);
        log.info("Floor stats successfully fetched");
        log.info(returnStats.toString());
        return returnStats;
    }

    public Stats getBuildingStats(Long buildingId) {
        Building building = buildingRepository.findById(buildingId).get();
        Stats returnStats = new Stats(building);
        log.info("Building stats successfully fetched");
        log.info(returnStats.toString());
        return returnStats;
    }
}
