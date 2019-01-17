package com.builder.demo.service.impl;

import com.builder.demo.exception.service.RoomServiceException;
import com.builder.demo.model.impl.LocationVisitor;
import com.builder.demo.model.impl.Room;
import com.builder.demo.model.error.ErrorMessages;
import com.builder.demo.model.impl.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.service.RoomService;
import com.builder.demo.shared.dto.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    BuildingRepository buildingRepository;
    RoomRepository roomRepository;
    FloorRepository floorRepository;
    ModelMapper modelMapper;
    LocationVisitor visitor;

    public RoomServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository, FloorRepository floorRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
        this.visitor = new LocationVisitor();
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
        savedRoom.accept(visitor);
        return modelMapper.map(savedRoom, RoomDto.class);
    }

    @Override
    public RoomDto getRoom(Long buildingId, Long floorId, Long roomId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new RoomServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findById(floorId).isPresent())
            throw new RoomServiceException(ErrorMessages.FLOOR_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!roomRepository.findById(roomId).isPresent())
            throw new RoomServiceException(ErrorMessages.ROOM_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        Room room = roomRepository.findById(roomId).get();
        Stats buildingStats = new Stats(room);
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);
        roomDto.setArea(buildingStats.getArea());
        roomDto.setCube(buildingStats.getCube());
        roomDto.setHeating(buildingStats.getHeating());
        roomDto.setLight(buildingStats.getLight());
        return roomDto;
    }

    @Override
    public List<RoomDto> getRooms(Long buildingId, Long floorId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new RoomServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findById(floorId).isPresent())
            throw new RoomServiceException(ErrorMessages.FLOOR_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!roomRepository.findByBuildingAndFloor(
                buildingRepository.findById(buildingId).get(),
                floorRepository.findById(floorId).get()).isPresent())
            return null;
        List<RoomDto> roomDtos = new ArrayList<>();
        List<Room> rooms = roomRepository.findByBuildingAndFloor(
                buildingRepository.findById(buildingId).get(),
                floorRepository.findById(floorId).get()).get();
        rooms.forEach(room -> {
            roomDtos.add(getRoom(buildingId, floorId, room.getRoomId()));
        });
        return roomDtos;
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto, Long buildingId, Long floorId, Long roomId) {
        if(!buildingRepository.findById(buildingId).isPresent())
            throw new RoomServiceException(ErrorMessages.BUILDING_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!floorRepository.findById(floorId).isPresent())
            throw new RoomServiceException(ErrorMessages.FLOOR_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        if(!roomRepository.findById(roomId).isPresent())
            throw new RoomServiceException(ErrorMessages.ROOM_NOT_EXIST.getErrorMessage(), HttpStatus.BAD_REQUEST);
        Room room = roomRepository.findById(roomId).get();
        Room updatedRoom = modelMapper.map(roomDto, Room.class);
        updatedRoom.setRoomId(room.getRoomId());
        updatedRoom.setBuilding(room.getBuilding());
        updatedRoom.setFloor(room.getFloor());
        Room returnedRoom = roomRepository.save(updatedRoom);
        return modelMapper.map(returnedRoom, RoomDto.class);
    }
}
