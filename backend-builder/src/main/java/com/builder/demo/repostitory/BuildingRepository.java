package com.builder.demo.repostitory;

import com.builder.demo.model.impl.Building;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {
    Optional<Building> findByName(String name);
    List<Building> findAll();
}
