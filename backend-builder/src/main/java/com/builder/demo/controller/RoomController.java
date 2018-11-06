package com.builder.demo.controller;

import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.service.impl.RoomServiceImpl;
import com.builder.demo.shared.dto.FloorDto;
import com.builder.demo.shared.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildings/{buildingId}/floors")
public class RoomController {

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    FloorServiceImpl floorService;

    @Autowired
    RoomServiceImpl roomService;

    @PostMapping
    @RequestMapping(path = "/{floorId}/rooms",
            consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public RoomDto createRoom(@PathVariable String buildingId, @PathVariable String floorId, @RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto, Long.parseLong(buildingId), Long.parseLong(floorId));
    }
}