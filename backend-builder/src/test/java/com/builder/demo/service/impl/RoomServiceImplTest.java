package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.shared.dto.RoomDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;


public class RoomServiceImplTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    FloorRepository floorRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    RoomServiceImpl roomService;

    Room room;
    public static final Long BUILDING_ID = 1L;
    public static final Long FLOOR_ID = 1L;
    public static final Long ROOM_ID = 1L;
    public static final String ROOM_NAME = "TEST";
    public static final float AREA = 1.1f;
    public static final float CUBE = 1.1f;
    public static final float HEATING = 1.1f;
    public static final float LIGHT = 1.1f;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        room = new Room();
        room.setRoomId(ROOM_ID);
        room.setRoomName(ROOM_NAME);
        room.setFloor(new Floor());
        room.setBuilding(new Building());
        room.setArea(AREA);
        room.setCube(CUBE);
        room.setHeating(HEATING);
        room.setLight(LIGHT);
    }

    @Test
    public void createRoom() {
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Floor()));

        RoomDto roomDto = RoomDto.builder()
                .roomName(ROOM_NAME)
                .area(AREA)
                .cube(CUBE)
                .heating(HEATING)
                .light(LIGHT).build();
        RoomDto storedDto = roomService.createRoom(roomDto, BUILDING_ID, FLOOR_ID);

        assertNotNull(storedDto);
        assertEquals(roomDto.getRoomName(), storedDto.getRoomName());
        assertEquals(room.getRoomId(), storedDto.getRoomId());
        assertEquals(roomDto.getCube(), storedDto.getCube(), 0.0f);

        verify(roomRepository, times(1)).save(any(Room.class));


    }
}