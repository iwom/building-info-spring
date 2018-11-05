package com.builder.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@ToString
public class Building implements Serializable {
    private static final long serialVersionUID = 5313493413859894401L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Floor> floorList;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Room> roomList;

    public Long getId() {
        return id;
    }
}
