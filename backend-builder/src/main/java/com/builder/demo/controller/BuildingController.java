package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BuildingController handles REST API requests from clients.
 * GET and POST requests available
 * It returns proper data transfer objects of type BuildingDTO
 * @see BuildingDto
 * @author iwom
 */
@RestController
@Slf4j
@CrossOrigin
public class BuildingController {

    /**
     * Autowired service for CRUD operations
     * @see BuildingServiceImpl
     */
    @Autowired
    BuildingServiceImpl buildingService;

    /**
     * Autowired service for Stats computing
     * @see StatsServiceImpl
     */
    @Autowired
    StatsServiceImpl statsService;


    /**
     * HTTP POST method
     * @param buildingDto BuildingDto to be created via {@link BuildingServiceImpl}
     * @return HTTP response containing {@link com.builder.demo.shared.dto.BuildingDto} object in response body
     */
    @PostMapping
    @RequestMapping(path = "/buildings",
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public BuildingDto createBuilding(@RequestBody BuildingDto buildingDto) {
        return buildingService.createBuilding(buildingDto);
    }

    /**
     * HTTP GET method
     * @param buildingId to find building by its id
     * @return HTTP response containing {@link com.builder.demo.shared.dto.BuildingDto} object in response body
     */
    @GetMapping
    @RequestMapping(path = "/buildings/{buildingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingDto getBuilding(@PathVariable String buildingId) {
        return buildingService.getBuilding(Long.parseLong(buildingId));
    }

    /**
     * HTTP GET method
     * @return HTTP response containing a list of {@link com.builder.demo.shared.dto.BuildingDto} object in response body
     */
    @GetMapping
    @RequestMapping(path = "/buildings", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BuildingDto> getAllBuildings() {
        return buildingService.getBuildings();
    }
}
