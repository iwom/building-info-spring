package com.builder.demo.service.impl;

import com.builder.demo.model.Building;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.shared.dto.BuildingDto;
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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class BuildingServiceImplTest {

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    BuildingServiceImpl buildingService;

    Building building;

    private static final String BUILDING_NAME = "TEST";
    private static final Long BUILDING_ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        building = new Building();
        building.setId(BUILDING_ID);
        building.setName(BUILDING_NAME);
        building.setFloorList(null);
        building.setRoomList(null);
    }

    @Test
    public void createBuilding() {
        when(buildingRepository.save(any(Building.class))).thenReturn(building);

        BuildingDto buildingDto = BuildingDto.builder().name(BUILDING_NAME).build();
        BuildingDto storedDto = buildingService.createBuilding(buildingDto);

        assertNotNull(storedDto);
        assertEquals(buildingDto.getName(), storedDto.getName());
        assertEquals(building.getId(), storedDto.getId());

        verify(buildingRepository, times(1)).save(any(Building.class));
    }
}