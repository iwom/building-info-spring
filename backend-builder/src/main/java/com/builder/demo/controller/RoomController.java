package com.builder.demo.controller;

import com.builder.demo.model.impl.Stats;
import com.builder.demo.service.impl.BuildingServiceImpl;
import com.builder.demo.service.impl.FloorServiceImpl;
import com.builder.demo.service.impl.RoomServiceImpl;
import com.builder.demo.service.impl.StatsServiceImpl;
import com.builder.demo.shared.dto.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/buildings/{buildingId}/floors")
@Slf4j
public class RoomController {

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    FloorServiceImpl floorService;

    @Autowired
    RoomServiceImpl roomService;

    @Autowired
    StatsServiceImpl statsService;

    @PostMapping
    @RequestMapping(path = "/{floorId}/rooms", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    public RoomDto createRoom(@PathVariable String buildingId, @PathVariable String floorId, @RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto, Long.parseLong(buildingId), Long.parseLong(floorId));
    }

    @RequestMapping(path = "/{floorId}/rooms/{roomId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RoomDto returnRoomStats(@PathVariable String buildingId, @PathVariable String floorId, @PathVariable String roomId) {
        return roomService.getRoom(Long.parseLong(buildingId), Long.parseLong(floorId), Long.parseLong(roomId));
    }

    @GetMapping
    @RequestMapping(path = "/{floorId}/rooms", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RoomDto> getAllRooms(@PathVariable String buildingId, @PathVariable String floorId) {
        return roomService.getRooms(Long.parseLong(buildingId), Long.parseLong(floorId));
    }
}
