package com.builder.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Level {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
