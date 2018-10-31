package com.builder.demo.repostitory;

import com.builder.demo.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class RoomRepository implements PagingAndSortingRepository<Room, Long> {
}
