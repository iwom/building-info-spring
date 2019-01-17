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
 * FloorEntity is a minor abstraction unit in this project.
 * The entity holds information about all Rooms associated with the floor
 * A Floor can not exist without a Building
 * @see Building
 * @see Room
 * @author iwom
 */
@Entity
@Table(name = "floors")
@Getter
@Setter
@ToString
public class Floor implements Serializable, Location {
    /**
     * UID for serialization purposes
     */
    private static final long serialVersionUID = 5313493413859894402L;
    /**
     * Unique id which allows identification of a floor with REST API
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long floorId;
    /**
     * User chosen name of the floor
     */
    private String floorName;

    /**
     * Association with {@link Building}
     */
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    /**
     * Collection of rooms associated with the floor
     */
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    @Getter
    @ToString.Exclude
    private List<Room> roomList;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
