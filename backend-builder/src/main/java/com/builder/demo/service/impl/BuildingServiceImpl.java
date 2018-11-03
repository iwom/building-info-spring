package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.BuildingService;
import com.builder.demo.shared.dto.BuildingDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class BuildingServiceImpl implements BuildingService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.map(buildingDto, Building.class);
        Building savedBuilding = buildingRepository.save(building);
        return modelMapper.map(savedBuilding, BuildingDto.class);
    }
}
