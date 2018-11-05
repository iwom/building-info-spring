package com.builder.demo.service.impl;

import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import com.builder.demo.model.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.FloorService;
import com.builder.demo.shared.dto.FloorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FloorServiceImpl implements FloorService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    ModelMapper modelMapper;

    public FloorServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public FloorDto createFloor(FloorDto floorDto, Long buildingId) {
        Floor floor = modelMapper.map(floorDto, Floor.class);
        floor.setBuilding(buildingRepository.findById(buildingId).get());
        Floor savedFloor = floorRepository.save(floor);
        return modelMapper.map(savedFloor, FloorDto.class);
    }
}
