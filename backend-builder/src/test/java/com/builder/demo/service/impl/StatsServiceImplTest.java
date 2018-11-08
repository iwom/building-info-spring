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
import static com.builder.demo.service.impl.RoomServiceImplTest.FLOOR_ID;
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
import static org.mockito.Mockito.eq;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;


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

    Floor[] floorArr;
    public static final int FLOOR_SZ = 4;
    public static final Long[] FLOOR_IDX = {1L, 2L, 3L, 4L};
    public static final float[] FLOOR_AREAS = {2.3f, 2.7f, 3.1f, 3.5f};
    public static final float[] FLOOR_CUBES = {4.3f, 4.7f, 5.1f, 5.5f};
    public static final float[] FLOOR_LIGHTS = {2.74f, 2.48f, 2.29f, 2.14f};
    public static final float[] FLOOR_HEATINGS = {1.93f, 1.85f, 1.78f, 1.73f};

    Building[] buildingArr;
    public static final int BUILDING_SZ = 2;
    public static final Long[] BUILDING_IDX = {1L, 2L};
    public static final float[] BUILDING_AREAS = {5.0f, 6.6f};
    public static final float[] BUILDING_CUBES = {9.0f, 10.6f};
    public static final float[] BUILDING_LIGHTS = {2.61f, 2.22f};
    public static final float[] BUILDING_HEATINGS = {1.89f, 1.76f};

    public static final float DELTA = 0.01f;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(Stats.class);

        roomArr = new Room[ROOM_SZ];
        floorArr = new Floor[FLOOR_SZ];
        buildingArr = new Building[BUILDING_SZ];

        for (int i = 0; i < ROOM_SZ; i++) {
            Room room = new Room();
            room.setRoomId(ROOM_IDX[i]);
            room.setArea(ROOM_AREAS[i]);
            room.setCube(ROOM_CUBES[i]);
            room.setLight(ROOM_LIGHTS[i]);
            room.setHeating(ROOM_HEATINGS[i]);
            roomArr[i] = room;
        }

        for (int i = 0; i < FLOOR_SZ; i++) {
            Floor floor = new Floor();
            floor.setFloorId(FLOOR_IDX[i]);
            floor.setRoomList(new ArrayList<Room>(Arrays.asList(roomArr).subList(2*i, 2*i + 2)));
            floorArr[i] = floor;
        }

        for (int i = 0; i < BUILDING_SZ; i++) {
            Building building = new Building();
            building.setId(BUILDING_IDX[i]);
            building.setFloorList(Arrays.asList(floorArr).subList(2*i, 2*i + 2));
            buildingArr[i] = building;
        }
    }

    @Test
    public void getRoomStatsTest() throws Exception {
        for (int i = 0; i < ROOM_SZ; i++) {
            when(roomRepository.findByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(
                    eq(BUILDING_IDX[i/4]),
                    eq(FLOOR_IDX[i/2]),
                    eq(ROOM_IDX[i])
            )).thenReturn(Optional.of(roomArr[i]));
        }

        for (int i = 0; i < ROOM_SZ; i++) {
            Stats stats = statsService.getRoomStats(BUILDING_IDX[i/4], FLOOR_IDX[i/2], ROOM_IDX[i]);
            assertEquals(roomArr[i].getArea(), stats.getArea(), DELTA);
            assertEquals(roomArr[i].getCube(), stats.getCube(), DELTA);
            assertEquals(roomArr[i].getLight(), stats.getLight(), DELTA);
            assertEquals(roomArr[i].getHeating(), stats.getHeating(), DELTA);
        }
    }

    @Test
    public void getFloorStatsTest() throws Exception {
        for (int i = 0; i < FLOOR_SZ; i++) {
            when(floorRepository.findByBuildingIdEqualsAndFloorIdEquals(
                    eq(BUILDING_IDX[i/2]),
                    eq(FLOOR_IDX[i])
            )).thenReturn(Optional.of(floorArr[i]));
        }

        for (int i = 0; i < FLOOR_SZ; i++) {
            Stats stats = statsService.getFloorStats(BUILDING_IDX[i/2], FLOOR_IDX[i]);
            assertEquals(FLOOR_AREAS[i], stats.getArea(), DELTA);
            assertEquals(FLOOR_CUBES[i], stats.getCube(), DELTA);
            assertEquals(FLOOR_LIGHTS[i], stats.getLight(), DELTA);
            assertEquals(FLOOR_HEATINGS[i], stats.getHeating(), DELTA);
        }
    }

    @Test
    public void getBuildingStatsTest() throws Exception {
        for (int i = 0; i < BUILDING_SZ; i++) {
            when(buildingRepository.findById(eq(BUILDING_IDX[i]))).thenReturn(Optional.of(buildingArr[i]));
        }

        for (int i = 0; i < BUILDING_SZ; i++) {
            Stats stats = statsService.getBuildingStats(BUILDING_IDX[i]);
            assertEquals(BUILDING_AREAS[i], stats.getArea(), DELTA);
            assertEquals(BUILDING_CUBES[i], stats.getCube(), DELTA);
            assertEquals(BUILDING_LIGHTS[i], stats.getLight(), DELTA);
            assertEquals(BUILDING_HEATINGS[i], stats.getHeating(), DELTA);
        }
    }
}
