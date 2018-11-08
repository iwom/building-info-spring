package com.builder.demo.repostitory;


import com.builder.demo.model.Floor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends PagingAndSortingRepository<Floor, Long> {
    Optional<Floor> findByFloorName(String floorName);
    Optional<Floor> findByBuildingIdEqualsAndFloorIdEquals(Long buildingId, Long floorId);
}
