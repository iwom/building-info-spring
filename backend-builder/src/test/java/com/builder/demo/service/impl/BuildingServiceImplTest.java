package com.builder.demo.service.impl;

import com.builder.demo.exception.service.BuildingServiceException;
import com.builder.demo.model.impl.Building;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.shared.dto.BuildingDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BuildingServiceImplTest {

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    BuildingServiceImpl buildingService;

    Building building;
    BuildingDto buildingDto;

    private static final String BUILDING_NAME = "TEST";
    private static final Long BUILDING_ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        buildingDto = BuildingDto.builder().name(BUILDING_NAME).build();
        building = new Building();
        building.setId(BUILDING_ID);
        building.setName(BUILDING_NAME);
        building.setFloorList(null);
        building.setRoomList(null);
    }

    @Test
    public void createBuilding() {
        when(buildingRepository.save(any(Building.class))).thenReturn(building);
        when(buildingRepository.findByName(any(String.class))).thenReturn(Optional.ofNullable(null));

        BuildingDto storedDto = buildingService.createBuilding(buildingDto);

        assertNotNull(storedDto);
        assertEquals(buildingDto.getName(), storedDto.getName());
        assertEquals(building.getId(), storedDto.getId());

        verify(buildingRepository, times(1)).save(any(Building.class));
    }

    @Test(expected = BuildingServiceException.class)
    public void createBuilding_throwsRecordAlreadyExists() {
        when(buildingRepository.findByName(any(String.class))).thenReturn(Optional.of(new Building()));

        BuildingDto storedDto = buildingService.createBuilding(buildingDto);

    }
}