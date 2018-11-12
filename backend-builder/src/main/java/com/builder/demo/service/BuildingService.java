package com.builder.demo.service;

import com.builder.demo.shared.dto.BuildingDto;

/**
 * BuildingService - interface allowing CRUD operations on {@link com.builder.demo.shared.dto.BuildingDto}
 * This service uses {@link com.builder.demo.repostitory.BuildingRepository} to communicate with mysql database
 * @author iwom
 */
public interface BuildingService {
    /**
     * @param buildingDto BuildingDto to be created and persisted to database as {@link com.builder.demo.model.Building}
     * @return {@link com.builder.demo.shared.dto.BuildingDto} if the object was succesfully created, 4xx error otherwise.
     */
    BuildingDto createBuilding(BuildingDto buildingDto);
}
