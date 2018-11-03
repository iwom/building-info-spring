package com.builder.demo.repostitory;


import com.builder.demo.model.Level;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends PagingAndSortingRepository<Level, Long> {
}
