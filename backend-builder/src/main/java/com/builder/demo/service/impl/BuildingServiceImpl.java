package com.builder.demo.service.impl;

import com.builder.demo.model.impl.Building;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.model.impl.LocationVisitor;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.BuildingService;
import com.builder.demo.shared.dto.BuildingDto;
import com.builder.demo.exception.service.BuildingServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class BuildingServiceImpl implements BuildingService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    LocationVisitor visitor;
    ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
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
}
