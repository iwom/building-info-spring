package com.builder.demo.controller;

import com.builder.demo.model.Stats;
import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.BuildingDto;
import com.builder.demo.shared.dto.FloorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;

@RestController
@RequestMapping("/buildings")
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

    @RequestMapping(path = "/{buildingId}/floors/{floorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stats returnFloorStats(@PathVariable String buildingId, @PathVariable String floorId) throws NoSuchObjectException {
        Stats stats = statsService.getFloorStats(Long.parseLong(buildingId), Long.parseLong(floorId));

        if (stats.getArea() == 0 || stats.getCube() == 0 || stats.getHeating() == 0 || stats.getLight() == 0)
            throw new NoSuchObjectException("Object not found");
        else
            return stats;
    }
}
