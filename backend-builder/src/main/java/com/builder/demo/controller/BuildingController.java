package com.builder.demo.controller;

import com.builder.demo.model.Stats;
import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;

@RestController
@Slf4j
public class BuildingController {

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    StatsServiceImpl statsService;


    @PostMapping
    @RequestMapping(path = "/buildings",
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public BuildingDto createBuilding(@RequestBody BuildingDto buildingDto) {
        return buildingService.createBuilding(buildingDto);
    }

    @RequestMapping(path = "/buildings/{buildingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stats returnBuildingStats(@PathVariable String buildingId) throws NoSuchObjectException {
        Stats stats = statsService.getBuildingStats(Long.parseLong(buildingId));

        if (stats.getArea() == 0 || stats.getCube() == 0 || stats.getHeating() == 0 || stats.getLight() == 0)
            throw new NoSuchObjectException("Object not found");
        else
            return stats;
    }
}
