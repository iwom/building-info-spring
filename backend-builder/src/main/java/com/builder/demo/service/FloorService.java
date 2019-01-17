package com.builder.demo.service;

import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.shared.dto.FloorDto;

import java.util.List;

/**
 * FloorService - interface allowing CRUD operations on {@link com.builder.demo.shared.dto.FloorDto}
 * This service uses {@link com.builder.demo.repostitory.FloorRepository} to communicate with mysql database
 * @author iwom
 */
public interface FloorService {
    /**
     * @param floorDto FloorDto to be created and persisted to database as {@link Floor}
     * @param buildingId parent id enabling to establish relationship between {@link Floor} and {@link Building}
     * @return {@link com.builder.demo.shared.dto.FloorDto} if the object was succesfully created, 4xx error otherwise.
     */
    FloorDto createFloor(FloorDto floorDto, Long buildingId);

    /**
     * @param buildingId Building Id to search in database
     * @param floorId Floor Id to search in database
     * @return {@link com.builder.demo.shared.dto.FloorDto} if object with requested params value exists in database, 4xx error otherwise.
     */
    FloorDto getFloor(Long buildingId, Long floorId);

    /**
     * @param buildingId Building Id to search in database
     * @return List of {@link com.builder.demo.shared.dto.FloorDto} if the set of objects (with matching buildingId) exists in database, empty list otherwise.
     */
    List<FloorDto> getFloors(Long buildingId);
}
