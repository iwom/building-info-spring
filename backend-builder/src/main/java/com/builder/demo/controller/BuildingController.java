package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {

    BuildingServiceImpl buildingService;

    @PostMapping
    @RequestMapping(path = "/buildings",
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public BuildingDto createBuilding(@RequestBody BuildingDto buildingDto) {
        return buildingService.createBuilding(buildingDto);
    }

}
