package com.builder.demo.service.impl;

import com.builder.demo.exception.service.RoomServiceException;
import com.builder.demo.model.impl.Building;
import com.builder.demo.model.impl.Floor;
import com.builder.demo.model.impl.Room;
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
    Room room2;
    RoomDto roomDto;
    RoomDto roomDto2;

    public static final Long BUILDING_ID = 1L;
    public static final Long FLOOR_ID = 1L;
    public static final Long ROOM_ID = 1L;
    public static final Long ANOTHER_ROOM_ID = 2L;
    public static final String ROOM_NAME = "TEST";
    public static final String ANOTHER_ROOM_NAME = "ANOTHER";
    public static final float AREA = 1.1f;
    public static final float CUBE = 1.1f;
    public static final float HEATING = 1.1f;
    public static final float LIGHT = 1.1f;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roomDto = RoomDto.builder()
                .roomName(ROOM_NAME)
                .area(AREA)
                .cube(CUBE)
                .heating(HEATING)
                .light(LIGHT).build();
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
        when(roomRepository.findByRoomName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Floor()));


        RoomDto storedDto = roomService.createRoom(roomDto, BUILDING_ID, FLOOR_ID);

        assertNotNull(storedDto);
        assertEquals(roomDto.getRoomName(), storedDto.getRoomName());
        assertEquals(room.getRoomId(), storedDto.getRoomId());
        assertEquals(roomDto.getCube(), storedDto.getCube(), 0.0f);

        verify(roomRepository, times(1)).save(any(Room.class));


    }

    @Test(expected = RoomServiceException.class)
    public void createRoom_throwsRecordAlreadyExists() {
        when(roomRepository.findByRoomName(any(String.class))).thenReturn(Optional.of(new Room()));
        RoomDto storedDto = roomService.createRoom(roomDto, BUILDING_ID, FLOOR_ID);
    }

    @Test(expected = RoomServiceException.class)
    public void createRoom_throwsBuildingNotExists() {
        when(roomRepository.findByRoomName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Floor()));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        RoomDto storedDto = roomService.createRoom(roomDto, BUILDING_ID, FLOOR_ID);
    }

    @Test(expected = RoomServiceException.class)
    public void createRoom_throwsFloorNotExists() {
        when(roomRepository.findByRoomName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        RoomDto storedDto = roomService.createRoom(roomDto, BUILDING_ID, FLOOR_ID);
    }

    @Test
    public void getRoom() {
        when(roomRepository.findById(any(Long.class))).thenReturn(Optional.of(room));
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Floor()));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        RoomDto fetchedDto = roomService.getRoom(BUILDING_ID, FLOOR_ID, ROOM_ID);

        assertNotNull(fetchedDto);
        assertEquals(ROOM_ID, fetchedDto.getRoomId());
        assertEquals(ROOM_NAME, fetchedDto.getRoomName());
        assertEquals(AREA, fetchedDto.getArea(), 0.0f);

        verify(roomRepository, times(2)).findById(any(Long.class));
    }
    
    @Test
    public void getRooms() {
        room2 = new Room();
        room2.setRoomId(ANOTHER_ROOM_ID);
        room2.setRoomName(ANOTHER_ROOM_NAME);
        room2.setFloor(new Floor());
        room2.setBuilding(new Building());
        room2.setArea(AREA + 1.5f);
        room2.setCube(CUBE + 1.5f);
        room2.setHeating(HEATING + 1.5f);
        room2.setLight(LIGHT + 1.5f);
        when(floorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Floor()));
        when(buildingRepository.findById(any(Long.class))).thenReturn(Optional.of(new Building()));
        when(roomRepository.findByBuildingAndFloor(any(Building.class), any(Floor.class)))
                .thenReturn(Optional.of(new ArrayList<>(Arrays.asList(room, room2))));
        List<RoomDto> fetchedDtos = roomService.getRooms(BUILDING_ID, FLOOR_ID);

        assertNotNull(fetchedDtos);
        assertEquals(2, fetchedDtos.size());
        assertEquals(room.getRoomName(), fetchedDtos.get(0).getRoomName());
        assertEquals(room2.getRoomName(), fetchedDtos.get(1).getRoomName());
        assertEquals(room.getRoomId(), fetchedDtos.get(0).getRoomId());
        assertEquals(room2.getRoomId(), fetchedDtos.get(1).getRoomId());
        assertEquals(room.getArea(), fetchedDtos.get(0).getArea(), 0.0f);
        assertEquals(room2.getArea(), fetchedDtos.get(1).getArea(), 0.0f);
    }
}