package com.builder.demo.model;

import lombok.Getter;
import java.util.List;

public class Stats {

    @Getter
    private final float area;
    @Getter
    private final float cube;
    @Getter
    private final float heating;
    @Getter
    private final float light;

    public Stats(Room room) {
        area = room.getArea();
        cube = room.getCube();
        light = room.getLight();
        heating = room.getHeating();
    }

    public Stats(Floor floor) {
        float newArea = 0, newCube = 0, newLight = 0, newHeating = 0;

        for (Room room : floor.getRoomList()) {
            newArea += room.getArea();
            newCube += room.getCube();
            newLight += room.getLight();
            newHeating += room.getHeating();
        }

        area = newArea;
        cube = newCube;
        light = newLight / newArea;
        heating = newHeating / newCube;
    }

    public Stats(Building building) {
        float newArea = 0, newCube = 0, newLight = 0, newHeating = 0;
        List<Floor> floorList = building.getFloorList();
        int floorListSize = floorList.size();

        for (Floor floor : building.getFloorList()) {
            Stats floorStats = new Stats(floor);
            newArea += floorStats.getArea();
            newCube += floorStats.getCube();
            newLight += floorStats.getLight();
            newHeating += floorStats.getHeating();
        }

        area = newArea;
        cube = newCube;
        light = newLight / floorListSize;
        heating = newHeating / floorListSize;
    }
}
