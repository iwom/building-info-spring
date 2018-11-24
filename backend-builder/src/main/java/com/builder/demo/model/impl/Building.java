package com.builder.demo.model.impl;

import com.builder.demo.model.Location;
import com.builder.demo.model.Visitor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * BuidlingEntity is the major abstraction unit in this project.
 * The entity holds information about all its children
 * @see Floor
 * @see Room
 * @author iwom
 */
@Entity
@Table(name = "buildings")
@Getter
@Setter
@ToString
public class Building implements Serializable, Location {
    /**
     * UID for serialization purposes
     */
    private static final long serialVersionUID = 5313493413859894401L;
    /**
     * Unique id which allows identification of a building with REST API
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    /**
     * User chosen name of the building
     */
    private String name;

    /**
     * Collection of floors associated with the building
     */
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    @Getter
    @ToString.Exclude
    private List<Floor> floorList;

    /**
     * Collection of rooms associated with the building
     */
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Room> roomList;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
