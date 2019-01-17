package com.builder.demo.service.impl;

import com.builder.demo.exception.service.BuildingServiceException;
import com.builder.demo.model.impl.Building;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.repostitory.RoomRepository;
import com.builder.demo.shared.dto.BuildingDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BuildingServiceImplTest {

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    FloorRepository floorRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    FloorServiceImpl floorService;

    @InjectMocks
    BuildingServiceImpl buildingService;

    Building building;
    Building building2;
    BuildingDto buildingDto;
    BuildingDto buildingDto2;

    private static final String BUILDING_NAME = "TEST";
    private static final String ANOTHER_BUILDING_NAME = "ANOTHER";
    private static final Long BUILDING_ID = 1L;
    private static final Long ANOTHER_BUILDING_ID = 2L;

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

    @Test
    public void getEmptyBuilding() {
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(building));
        when(floorService.getFloors(any(Long.class))).thenReturn(null);
        BuildingDto fetchedDto = buildingService.getBuilding(BUILDING_ID);

        assertNotNull(fetchedDto);
        assertEquals(BUILDING_ID, fetchedDto.getId());
        assertEquals(BUILDING_NAME, fetchedDto.getName());

        verify(buildingRepository, times(2)).findById(any(Long.class));
    }

    @Test
    public void getBuildings() {
        building2 = new Building();
        building2.setId(ANOTHER_BUILDING_ID);
        building2.setName(ANOTHER_BUILDING_NAME);
        when(buildingRepository.findById(BUILDING_ID)).thenReturn(Optional.of(building));
        when(buildingRepository.findById(ANOTHER_BUILDING_ID)).thenReturn(Optional.of(building2));
        when(buildingRepository.findAll())
                .thenReturn(new ArrayList<>(Arrays.asList(building, building2)));
        when(floorService.getFloors(any(Long.class))).thenReturn(null);
        List<BuildingDto> fetchedDtos = buildingService.getBuildings();
         
        assertNotNull(fetchedDtos);
        assertEquals(2, fetchedDtos.size());
        assertEquals(building.getName(), fetchedDtos.get(0).getName());
        assertEquals(building2.getName(), fetchedDtos.get(1).getName());
        assertEquals(building.getId(), fetchedDtos.get(0).getId());
        assertEquals(building2.getId(), fetchedDtos.get(1).getId());
    }

    @Test
    public void getBuildings_empty() {
        when(buildingRepository.findAll()).thenReturn(new ArrayList<>());
        List<BuildingDto> emptyBuildingArray = buildingService.getBuildings();
        assertEquals(0, emptyBuildingArray.size());

        verify(buildingRepository, times(1)).findAll();
    }
}