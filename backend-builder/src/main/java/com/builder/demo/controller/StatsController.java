package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.RoomServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.builder.demo.model.Stats;

import java.rmi.NoSuchObjectException;

@RestController
@RequestMapping("/")
public class StatsController {

    @Autowired
    RoomServiceImpl roomService;

    @Autowired
    FloorServiceImpl floorService;

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    StatsServiceImpl statsService;

    @RequestMapping(path = "/buildings/{buildingId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stats returnBuildingStats(@PathVariable String buildingId) throws NoSuchObjectException {
        Stats stats = statsService.getBuildingStats(Long.parseLong(buildingId));

        if (stats.getArea() == 0 || stats.getCube() == 0 || stats.getHeating() == 0 || stats.getLight() == 0)
            throw new NoSuchObjectException("Object not found");
        else
            return stats;
    }

    @RequestMapping(path = "/buildings/{buildingId}/floors/{floorId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stats returnFloorStats(@PathVariable String buildingId, @PathVariable String floorId) throws NoSuchObjectException {
        Stats stats = statsService.getFloorStats(Long.parseLong(buildingId), Long.parseLong(floorId));

        if (stats.getArea() == 0 || stats.getCube() == 0 || stats.getHeating() == 0 || stats.getLight() == 0)
            throw new NoSuchObjectException("Object not found");
        else
            return stats;
    }

    @RequestMapping(path = "/buildings/{buildingId}/floors/{floorId}/rooms/{roomId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stats returnRoomStats(@PathVariable String buildingId, @PathVariable String floorId, @PathVariable String roomId) throws NoSuchObjectException {
        Stats stats = statsService.getRoomStats(Long.parseLong(buildingId), Long.parseLong(floorId), Long.parseLong(roomId));

        if (stats.getArea() == 0 || stats.getCube() == 0 || stats.getHeating() == 0 || stats.getLight() == 0)
            throw new NoSuchObjectException("Object not found");
        else
            return stats;
    }
}
