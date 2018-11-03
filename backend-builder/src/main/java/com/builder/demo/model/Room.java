package com.builder.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "rooms")
@Getter
@Setter
@ToString
public class Room implements Serializable {
    private static final long serialVersionUID = 5313493413859894399L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float area;
    private float cube;
    private float heating;
    private float light;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;



}
