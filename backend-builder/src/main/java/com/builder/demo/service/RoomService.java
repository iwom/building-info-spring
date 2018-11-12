package com.builder.demo.service;

import com.builder.demo.shared.dto.RoomDto;

/**
 * RoomService - interface allowing CRUD operations on {@link com.builder.demo.shared.dto.RoomDto}
 * This service uses {@link com.builder.demo.repostitory.RoomRepository} to communicate with mysql database
 * @author iwom
 */
public interface RoomService {
    /**
     * @param roomDto RoomDto to be created and persisted to database as {@link com.builder.demo.model.Room}
     * @param buildingId parent id enabling to establish relationship between {@link com.builder.demo.model.Room} and {@link com.builder.demo.model.Building}
     * @param floorId parent id enabling to establish relationship between {@link com.builder.demo.model.Room} and {@link com.builder.demo.model.Floor}
     * @return {@link com.builder.demo.shared.dto.RoomDto} if the object was succesfully created, 4xx error otherwise.
     */
    RoomDto createRoom(RoomDto roomDto, Long buildingId, Long floorId);
}
