package com.builder.demo.shared.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDto implements Serializable {
    private static final long serialVersionUID = 6835192601798364279L;
    private Long roomId;
    private String roomName;
    private float area;
    private float cube;
    private float heating;
    private float light;
}
