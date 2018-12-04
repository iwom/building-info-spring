package com.builder.demo.service;

import com.builder.demo.model.impl.Building;
import com.builder.demo.shared.dto.BuildingDto;

import java.util.List;

/**
 * BuildingService - interface allowing CRUD operations on {@link com.builder.demo.shared.dto.BuildingDto}
 * This service uses {@link com.builder.demo.repostitory.BuildingRepository} to communicate with mysql database
 * @author iwom
 */
public interface BuildingService {
    /**
     * @param buildingDto BuildingDto to be created and persisted to database as {@link Building}
     * @return {@link com.builder.demo.shared.dto.BuildingDto} if the object was succesfully created, 4xx error otherwise.
     */
    BuildingDto createBuilding(BuildingDto buildingDto);

    /**
     * @param buildingId Building Id to search in database
     * @return {@link com.builder.demo.shared.dto.BuildingDto} if the object exists in database, 4xx error otherwise.
     */
    BuildingDto getBuilding(Long buildingId);

    /**
     * @return List of {@link com.builder.demo.shared.dto.BuildingDto} if the set of objects exists in database, empty list otherwise.
     */
    List<BuildingDto> getBuildings();

}
