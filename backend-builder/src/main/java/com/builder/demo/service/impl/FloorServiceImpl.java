package com.builder.demo.service.impl;

import com.builder.demo.exception.service.FloorServiceException;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.model.impl.LocationVisitor;
import com.builder.demo.model.impl.Room;
import com.builder.demo.model.impl.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.FloorService;
import com.builder.demo.service.RoomService;
import com.builder.demo.shared.dto.FloorDto;
import com.builder.demo.shared.dto.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    RoomService roomService;
    ModelMapper modelMapper;
    LocationVisitor visitor;

    public FloorServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository, RoomService roomService) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.roomService = roomService;
        this.modelMapper = new ModelMapper();
        this.visitor = new LocationVisitor();
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
        savedFloor.accept(visitor);
        return modelMapper.map(savedFloor, FloorDto.class);
    }

    @Override
    public FloorDto getFloor(Long buildingId, Long floorId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new FloorServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findById(floorId).isPresent())
            throw new FloorServiceException(ErrorMessages.FLOOR_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        Floor floor = floorRepository.findById(floorId).get();
        Stats buildingStats = new Stats(floor);
        FloorDto floorDto = modelMapper.map(floor, FloorDto.class);
        floorDto.setArea(buildingStats.getArea());
        floorDto.setCube(buildingStats.getCube());
        floorDto.setHeating(buildingStats.getHeating());
        floorDto.setLight(buildingStats.getLight());
        floorDto.setRoomDtoList(roomService.getRooms(buildingId, floorId));
        return floorDto;
    }

    @Override
    public List<FloorDto> getFloors(Long buildingId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new FloorServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findAllByBuilding(
                buildingRepository.findById(buildingId).get()
        ).isPresent())
            return null;
        List<FloorDto> floorDtos = new ArrayList<>();
        List<Floor> floors = floorRepository.findAllByBuilding(
                buildingRepository.findById(buildingId).get()
        ).get();
        floors.forEach(floor -> {
            floorDtos.add(getFloor(buildingId, floor.getFloorId()));
        });
        return floorDtos;
    }
}
