package com.builder.demo.service.impl;

import com.builder.demo.exception.service.RoomServiceException;
import com.builder.demo.model.Room;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.RoomService;
import com.builder.demo.shared.dto.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.builder.demo.model.Stats;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    ModelMapper modelMapper;

    public RoomServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto, Long buildingId, Long floorId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new RoomServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findById(floorId).isPresent())
            throw new RoomServiceException(ErrorMessages.FLOOR_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(roomRepository.findByRoomName(roomDto.getRoomName()).isPresent())
            throw new RoomServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage(), HttpStatus.CONFLICT);
        Room room = modelMapper.map(roomDto, Room.class);
        room.setBuilding(buildingRepository.findById(buildingId).get());
        room.setFloor(floorRepository.findById(floorId).get());
        Room savedRoom = roomRepository.save(room);
        return modelMapper.map(savedRoom, RoomDto.class);
    }
}
