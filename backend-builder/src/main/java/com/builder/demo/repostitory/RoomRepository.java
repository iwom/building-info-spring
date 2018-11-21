package com.builder.demo.repostitory;

import com.builder.demo.model.impl.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    Optional<Room> findByRoomName(String name);
    Optional<Room> findByBuildingIdEqualsAndFloorFloorIdEqualsAndRoomId(Long buildingId, Long floorId, Long roomId);
    Optional<List<Room>> findAllByBuilding_IdAndFloor_FloorId(Long buildingId, Long floorId);
}
