package com.builder.demo.model.impl;

import com.builder.demo.model.Location;
import com.builder.demo.model.Visitor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * RoomEntity is a minor abstraction unit in this project.
 * The entity holds information about a single room as well as parent ids
 * A Room can not exist without a Floor
 * A Floor can not exist without a Building
 * @see Building
 * @see Floor
 * @author iwom
 */
@Entity
@Table(name = "rooms")
@Getter
@Setter
@ToString
public class Room implements Serializable, Location {
    /**
     * UID for serialization purposes
     */
    private static final long serialVersionUID = 5313493413859894399L;
    /**
     * Unique id which allows identification of a room with REST API
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    /**
     * User chosen name of the room
     */
    private String roomName;
    /**
     * Area of the room in square meters
     */
    @Getter
    private float area;
    /**
     * Cubic capacity in cubic meters
     */
    @Getter
    private float cube;
    /**
     * Heating consumption per room
     */
    @Getter
    private float heating;
    /**
     * Total lighting power
     */
    @Getter
    private float light;

    /**
     * Association with {@link Floor}
     */
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    /**
     * Association with {@link Building}
     */
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
