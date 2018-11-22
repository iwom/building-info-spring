package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @RequestMapping(path = "/buildings/{buildingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BuildingDto getBuilding(@PathVariable String buildingId) {
        return buildingService.getBuilding(Long.parseLong(buildingId));
    }

    @GetMapping
    @RequestMapping(path = "/buildings", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BuildingDto> getAllBuildings() {
        return buildingService.getBuildings();
    }
}
