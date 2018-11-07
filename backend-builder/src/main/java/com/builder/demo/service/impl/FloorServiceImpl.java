package com.builder.demo.service.impl;

import com.builder.demo.exception.service.FloorServiceException;
import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import com.builder.demo.model.Stats;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.FloorService;
import com.builder.demo.shared.dto.FloorDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new FloorServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(floorRepository.findByFloorName(floorDto.getFloorName()).isPresent())
            throw new FloorServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage(), HttpStatus.CONFLICT);
        Floor floor = modelMapper.map(floorDto, Floor.class);
        floor.setBuilding(buildingRepository.findById(buildingId).get());
        Floor savedFloor = floorRepository.save(floor);
        return modelMapper.map(savedFloor, FloorDto.class);
    }
}
