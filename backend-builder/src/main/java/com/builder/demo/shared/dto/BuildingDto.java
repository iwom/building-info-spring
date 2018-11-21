package com.builder.demo.shared.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingDto implements Serializable {
    private static final long serialVersionUID = 6835192601898364280L;
    private Long id;
    private String name;
    private List<FloorDto> floorDtoList;
    private float area;
    private float cube;
    private float heating;
    private float light;
}
