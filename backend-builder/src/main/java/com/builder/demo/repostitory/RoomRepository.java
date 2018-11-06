package com.builder.demo.repostitory;

import com.builder.demo.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    Optional<Room> findByRoomName(String name);
}
