package com.builder.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float area;
    private float cube;
    private float heating;
    private float light;
}
