package com.builder.demo.repostitory;


import com.builder.demo.model.Level;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class LevelRepository implements PagingAndSortingRepository<Level, Long> {
}
