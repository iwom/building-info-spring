package com.builder.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "floors")
@Getter
@Setter
@ToString
public class Floor implements Serializable {
    private static final long serialVersionUID = 5313493413859894402L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long floorId;
    private String floorName;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    @Getter
    private List<Room> roomList;
}
