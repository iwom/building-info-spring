package com.builder.demo.repostitory;

import com.builder.demo.model.Building;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {

}
