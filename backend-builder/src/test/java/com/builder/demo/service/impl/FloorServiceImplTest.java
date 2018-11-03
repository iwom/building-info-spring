package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.model.Floor;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.shared.dto.FloorDto;
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

import static org.junit.Assert.*;

public class FloorServiceImplTest {

    @Mock
    FloorRepository floorRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    FloorServiceImpl floorService;

    Floor floor;

    public static final Long BUILDING_ID = 1L;
    public static final Long FLOOR_ID = 1L;
    public static final String FLOOR_NAME = "TEST";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        floor = new Floor();
        floor.setFloorId(FLOOR_ID);
        floor.setFloorName(FLOOR_NAME);
        floor.setBuilding(new Building());
        floor.setRoomList(null);

    }

    @Test
    public void createFloor() {
        when(floorRepository.save(any(Floor.class))).thenReturn(floor);
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));

        FloorDto floorDto = FloorDto.builder().floorName(FLOOR_NAME).build();
        FloorDto storedDto = floorService.createFloor(floorDto, BUILDING_ID);

        assertNotNull(storedDto);
        assertEquals(floorDto.getFloorName(), storedDto.getFloorName());
        assertEquals(floor.getFloorId(), storedDto.getFloorId());

        verify(floorRepository, times(1)).save(any(Floor.class));
    }
}