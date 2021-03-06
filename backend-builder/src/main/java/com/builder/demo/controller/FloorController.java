package com.builder.demo.controller;

import com.builder.demo.model.impl.Stats;
import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.FloorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/buildings")
@Slf4j
@CrossOrigin
public class FloorController {

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    FloorServiceImpl floorService;

    @Autowired
    StatsServiceImpl statsService;

    @PostMapping
    @RequestMapping(path = "/{buildingId}/floors",
            consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public FloorDto createFloor(@PathVariable String buildingId, @RequestBody FloorDto floorDto) {
        return floorService.createFloor(floorDto, Long.parseLong(buildingId));
    }

    @GetMapping
    @RequestMapping(path = "/{buildingId}/floors/{floorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FloorDto returnFloorStats(@PathVariable String buildingId, @PathVariable String floorId) {
        return floorService.getFloor(Long.parseLong(buildingId), Long.parseLong(floorId));
    }

    @GetMapping
    @RequestMapping(path = "/{buildingId}/floors", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FloorDto> getAllFloors(@PathVariable String buildingId) {
        return floorService.getFloors(Long.parseLong(buildingId));
    }
}
