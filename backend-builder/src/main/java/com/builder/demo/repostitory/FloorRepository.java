package com.builder.demo.repostitory;


import com.builder.demo.model.impl.Floor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends PagingAndSortingRepository<Floor, Long> {
    Optional<Floor> findByFloorName(String floorName);
    Optional<Floor> findByBuildingIdEqualsAndFloorIdEquals(Long buildingId, Long floorId);
    Optional<List<Floor>> findAllByBuilding_Id(Long buildingId);
}
