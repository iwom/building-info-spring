package com.builder.demo.repostitory;

import com.builder.demo.model.Building;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class BuildingRepository implements PagingAndSortingRepository<Building, Long> {

}
