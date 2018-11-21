package com.builder.demo.service.impl;

import com.builder.demo.exception.service.FloorServiceException;
import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.shared.dto.FloorDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FloorServiceImplTest {

    @Mock
    FloorRepository floorRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    FloorServiceImpl floorService;

    Floor floor;
    FloorDto floorDto;

    public static final Long BUILDING_ID = 1L;
    public static final Long FLOOR_ID = 1L;
    public static final String FLOOR_NAME = "TEST";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        floorDto = FloorDto.builder().floorName(FLOOR_NAME).build();
        floor = new Floor();
        floor.setFloorId(FLOOR_ID);
        floor.setFloorName(FLOOR_NAME);
        floor.setBuilding(new Building());
        floor.setRoomList(null);

    }

    @Test
    public void createFloor() {
        when(floorRepository.save(any(Floor.class))).thenReturn(floor);
        when(floorRepository.findByFloorName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));

        FloorDto storedDto = floorService.createFloor(floorDto, BUILDING_ID);

        assertNotNull(storedDto);
        assertEquals(floorDto.getFloorName(), storedDto.getFloorName());
        assertEquals(floor.getFloorId(), storedDto.getFloorId());

        verify(floorRepository, times(1)).save(any(Floor.class));
    }

    @Test(expected = FloorServiceException.class)
    public void createFloor_throwsRecordAlreadyExists() {
        when(floorRepository.findByFloorName(any(String.class))).thenReturn(Optional.of(new Floor()));

        floorService.createFloor(floorDto, BUILDING_ID);
    }

    @Test(expected = FloorServiceException.class)
    public void createFloor_throwsBuildingNotExists() {
        when(floorRepository.findByFloorName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

        floorService.createFloor(floorDto, BUILDING_ID);
    }
}