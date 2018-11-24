package com.builder.demo.service.impl;

import com.builder.demo.exception.service.FloorServiceException;
import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.repostitory.BuildingRepository;
import com.builder.demo.repostitory.FloorRepository;
import com.builder.demo.service.RoomService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FloorServiceImplTest {

    @Mock
    FloorRepository floorRepository;

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    RoomService roomService;

    @InjectMocks
    FloorServiceImpl floorService;

    Floor floor;
    Floor floor2;
    FloorDto floorDto;
    FloorDto floorDto2;

    public static final Long BUILDING_ID = 1L;
    public static final Long FLOOR_ID = 1L;
    public static final Long ANOTHER_FLOOR_ID = 2L;
    public static final String FLOOR_NAME = "TEST";
    public static final String ANOTHER_FLOOR_NAME = "ANOTHER";

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

    @Test
    public void getEmptyFloor() {
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(floor));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        when(roomService.getRooms(any(Long.class), any(Long.class))).thenReturn(null);
        FloorDto fetchedDto = floorService.getFloor(BUILDING_ID, FLOOR_ID);

        assertNotNull(fetchedDto);
        assertEquals(FLOOR_ID, fetchedDto.getFloorId());
        assertEquals(FLOOR_NAME, fetchedDto.getFloorName());

        verify(floorRepository, times(2)).findById(any(Long.class));

    }

    @Test
    public void getFloors() {
        floor2 = new Floor();
        floor2.setBuilding(new Building());
        floor2.setFloorId(ANOTHER_FLOOR_ID);
        floor2.setFloorName(ANOTHER_FLOOR_NAME);
        when(floorRepository.findById(FLOOR_ID)).thenReturn(Optional.of(floor));
        when(floorRepository.findById(ANOTHER_FLOOR_ID)).thenReturn(Optional.of(floor2));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        when(floorRepository.findAllByBuilding(any(Building.class)))
                .thenReturn(Optional.of(new ArrayList<>(Arrays.asList(floor, floor2))));

        when(roomService.getRooms(any(Long.class), any(Long.class))).thenReturn(null);
        List<FloorDto> fetchedDtos = floorService.getFloors(BUILDING_ID);

        assertNotNull(fetchedDtos);
        assertEquals(2, fetchedDtos.size());
        assertEquals(floor.getFloorName(), fetchedDtos.get(0).getFloorName());
        assertEquals(floor2.getFloorName(), fetchedDtos.get(1).getFloorName());
        assertEquals(floor.getFloorId(), fetchedDtos.get(0).getFloorId());
        assertEquals(floor2.getFloorId(), fetchedDtos.get(1).getFloorId());
    }
}