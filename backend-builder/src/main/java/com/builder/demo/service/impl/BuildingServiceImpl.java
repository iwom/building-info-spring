package com.builder.demo.service.impl;

import com.builder.demo.model.impl.Building;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.model.impl.LocationVisitor;
import com.builder.demo.model.impl.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.BuildingService;
import com.builder.demo.service.FloorService;
import com.builder.demo.shared.dto.BuildingDto;
import com.builder.demo.exception.service.BuildingServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuildingServiceImpl implements BuildingService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    FloorService floorService;
    LocationVisitor visitor;
    ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository, FloorService floorService) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.floorService = floorService;
        this.modelMapper = new ModelMapper();
        this.visitor = new LocationVisitor();
    }

    @Override
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        if (buildingRepository.findByName(buildingDto.getName()).isPresent())
            throw new BuildingServiceException(ErrorMessages.RECORD_NOT_CREATED.getErrorMessage(), HttpStatus.CONFLICT);
        Building building = modelMapper.map(buildingDto, Building.class);
        Building savedBuilding = buildingRepository.save(building);
        savedBuilding.accept(visitor);
        return modelMapper.map(savedBuilding, BuildingDto.class);
    }

    @Override
    public BuildingDto getBuilding(Long buildingId) {
        if(!buildingRepository.findById(buildingId).isPresent()) {
            throw new BuildingServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        Building building = buildingRepository.findById(buildingId).get();
        Stats buildingStats = new Stats(building);
        BuildingDto buildingDto = modelMapper.map(building, BuildingDto.class);
        buildingDto.setArea(buildingStats.getArea());
        buildingDto.setCube(buildingStats.getCube());
        buildingDto.setHeating(buildingStats.getHeating());
        buildingDto.setLight(buildingStats.getLight());
        buildingDto.setFloorDtoList(floorService.getFloors(buildingId));
        return buildingDto;
    }

    @Override
    public List<BuildingDto> getBuildings() {
        List<BuildingDto> buildingDtos = new ArrayList<>();
        List<Building> buildings = buildingRepository.findAll();
        buildings.forEach(building -> {
            buildingDtos.add(getBuilding(building.getId()));
        });
        return buildingDtos;
    }
}
