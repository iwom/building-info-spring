package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import com.builder.demo.shared.dto.FloorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildings")
public class FloorController {

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    FloorServiceImpl floorService;

    @PostMapping
    @RequestMapping(path = "/{buildingId}/floors",
            consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public FloorDto createFloor(@PathVariable String buildingId, @RequestBody FloorDto floorDto) {
        return floorService.createFloor(floorDto, Long.parseLong(buildingId));
    }
}
