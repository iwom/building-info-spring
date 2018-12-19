package com.builder.demo.service;

import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.model.impl.Room;
import com.builder.demo.shared.dto.RoomDto;

import java.util.List;

/**
 * RoomService - interface allowing CRUD operations on {@link com.builder.demo.shared.dto.RoomDto}
 * This service uses {@link com.builder.demo.repostitory.RoomRepository} to communicate with mysql database
 * @author iwom
 */
public interface RoomService {
    /**
     * @param roomDto RoomDto to be created and persisted to database as {@link Room}
     * @param buildingId parent id enabling to establish relationship between {@link Room} and {@link Building}
     * @param floorId parent id enabling to establish relationship between {@link Room} and {@link Floor}
     * @return {@link com.builder.demo.shared.dto.RoomDto} if the object was succesfully created, 4xx error otherwise.
     */
    RoomDto createRoom(RoomDto roomDto, Long buildingId, Long floorId);

    /**
     * @param buildingId Building Id to search in database
     * @param floorId Floor Id to search in database
     * @param roomId Room Id to search in database
     * @return {@link com.builder.demo.shared.dto.RoomDto} if object with requested params value exists in database, 4xx error otherwise.
     */
    RoomDto getRoom(Long buildingId, Long floorId, Long roomId);

    /**
     * @param buildingId Building Id to search in database
     * @param floorId Floor Id to search in database
     * @return List of {@link com.builder.demo.shared.dto.RoomDto} if the set of objects (with matching buildingId and floorId) exists in database, empty list otherwise.
     */
    List<RoomDto> getRooms(Long buildingId, Long floorId);

    /**
     * @param roomDto RoomDto to be updated and persisted to database as {@link Room}
     * @param buildingId Building Id to search in database
     * @param floorId Floor Id to search in database
     * @param floorId Room Id to search in database, room of this id will be updated
     * @return {@link com.builder.demo.shared.dto.RoomDto} if object (with matching buildingId and floorId and roomId) exists in database and was updated, 4xx otherwise
     */
    RoomDto updateRoom(RoomDto roomDto, Long buildingId, Long floorId, Long roomId);
}
