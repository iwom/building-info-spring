package com.builder.demo.repostitory;

import com.builder.demo.model.Building;
import com.builder.demo.model.Floor;
import com.builder.demo.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    List<Room> findAllByBuildingIdEquals(Long buildingId);
    List<Room> findAllByBuildingIdEqualsAndFloorFloorIdEquals(Long buildingId, Long floorId);
    List<Room> findAllByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(Long buildingId, Long floorId, Long roomId);
}
