package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import com.builder.demo.model.Stats;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.shared.dto.BuildingDto;
import com.builder.demo.shared.dto.FloorDto;
import com.builder.demo.shared.dto.RoomDto;
import org.junit.Before;
import org.junit.Test;

import static com.builder.demo.service.impl.RoomServiceImplTest.BUILDING_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.*;


public class StatsServiceImplTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    FloorRepository floorRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    StatsServiceImpl statsService;

    Room[] roomArr;
    public static final int ROOM_SZ = 8;
    public static final Long[] ROOM_IDX = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};
    public static final float[] ROOM_AREAS = {1.1f, 1.2f, 1.3f, 1.4f, 1.5f, 1.6f, 1.7f, 1.8f};
    public static final float[] ROOM_CUBES = {2.1f, 2.2f, 2.3f, 2.4f, 2.5f, 2.6f, 2.7f, 2.8f};
    public static final float[] ROOM_LIGHTS = {3.1f, 3.2f, 3.3f, 3.4f, 3.5f, 3.6f, 3.7f, 3.8f};
    public static final float[] ROOM_HEATINGS = {4.1f, 4.2f, 4.3f, 4.4f, 4.5f, 4.6f, 4.7f, 4.8f};

    public static final int FLOOR_SZ = 4;
    public static final Long[] FLOOR_IDX = {1L, 2L, 3L, 4L};

    public static final int BUILDING_SZ = 2;
    public static final Long[] BUILDING_IDX = {1L, 2L};

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(Stats.class);

        roomArr = new Room[ROOM_SZ];

        for (int i = 0; i < ROOM_SZ; i++) {
            Room room = new Room();
            room.setRoomId(ROOM_IDX[i]);
            room.setArea(ROOM_AREAS[i]);
            room.setCube(ROOM_CUBES[i]);
            room.setLight(ROOM_LIGHTS[i]);
            room.setHeating(ROOM_HEATINGS[i]);
            roomArr[i] = room;
        }
    }

    @Test
    public void getBuildingStatsTest() throws Exception {
        for (int i = 0; i < BUILDING_SZ; i++) {
            when(roomRepository.findAllByBuildingIdEquals(BUILDING_IDX[i]))
                    .thenReturn(Arrays.asList(roomArr).subList(4*i, 4*i + 4));
        }

        Stats stats = statsService.getBuildingStats(1L);
        assertEquals(5.0f, stats.getArea(), 0.01f);
        assertEquals(9.0f, stats.getCube(), 0.01f);
        assertEquals(13.0f, stats.getLight(), 0.01f);
        assertEquals(17.0f, stats.getHeating(), 0.01f);

        stats = statsService.getBuildingStats(2L);
        assertEquals(6.6f, stats.getArea(), 0.01f);
        assertEquals(10.6f, stats.getCube(), 0.01f);
        assertEquals(14.6f, stats.getLight(), 0.01f);
        assertEquals(18.6f, stats.getHeating(), 0.01f);
    }

    @Test
    public void getFloorStatsTest() throws Exception {
        for (int i = 0; i < FLOOR_SZ; i++) {
            when(roomRepository.findAllByBuildingIdEqualsAndFloorFloorIdEquals(BUILDING_IDX[i/2], FLOOR_IDX[i]))
                    .thenReturn(Arrays.asList(roomArr).subList(2*i, 2*i + 2));
        }

        Stats stats = statsService.getFloorStats(1L, 1L);
        assertEquals(2.3f, stats.getArea(), 0.01f);
        assertEquals(4.3f, stats.getCube(), 0.01f);
        assertEquals(6.3f, stats.getLight(), 0.01f);
        assertEquals(8.3f, stats.getHeating(), 0.01f);

        stats = statsService.getFloorStats(1L, 2L);
        assertEquals(2.7f, stats.getArea(), 0.01f);
        assertEquals(4.7f, stats.getCube(), 0.01f);
        assertEquals(6.7f, stats.getLight(), 0.01f);
        assertEquals(8.7f, stats.getHeating(), 0.01f);

        stats = statsService.getFloorStats(2L, 3L);
        assertEquals(3.1f, stats.getArea(), 0.01f);
        assertEquals(5.1f, stats.getCube(), 0.01f);
        assertEquals(7.1f, stats.getLight(), 0.01f);
        assertEquals(9.1f, stats.getHeating(), 0.01f);

        stats = statsService.getFloorStats(2L, 4L);
        assertEquals(3.5f, stats.getArea(), 0.01f);
        assertEquals(5.5f, stats.getCube(), 0.01f);
        assertEquals(7.5f, stats.getLight(), 0.01f);
        assertEquals(9.5f, stats.getHeating(), 0.01f);
    }

    @Test
    public void getRoomStatsTest() throws Exception {
        for (int i = 0; i < ROOM_SZ; i++) {
            when(roomRepository.findAllByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(BUILDING_IDX[i / 4], FLOOR_IDX[i / 2], ROOM_IDX[i]))
                    .thenReturn(Arrays.asList(roomArr).subList(i, i + 1));
        }

        for (int i = 0; i < ROOM_SZ; i++) {
            Stats stats = statsService.getRoomStats(BUILDING_IDX[i / 4], FLOOR_IDX[i / 2], ROOM_IDX[i]);
            assertEquals(roomArr[i].getArea(), stats.getArea(), 0.01f);
            assertEquals(roomArr[i].getCube(), stats.getCube(), 0.01f);
            assertEquals(roomArr[i].getLight(), stats.getLight(), 0.01f);
            assertEquals(roomArr[i].getHeating(), stats.getHeating(), 0.01f);
        }
    }
}
