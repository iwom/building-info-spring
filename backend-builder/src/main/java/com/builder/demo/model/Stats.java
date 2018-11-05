package com.builder.demo.model;

import java.util.List;

public class Stats {

    private final float area;
    private final float cube;
    private final float heating;
    private final float light;

    public Stats(List<Room> roomList) {
        float newArea = 0, newCube = 0, newLight = 0, newHeating = 0;

        for (Room room : roomList) {
            newArea += room.getArea();
            newCube += room.getCube();
            newLight += room.getLight();
            newHeating += room.getHeating();
        }

        this.area = newArea;
        this.cube = newCube;
        this.light = newLight;
        this.heating = newHeating;
    }

    public float getArea() { return this.area; }
    public float getCube() { return this.cube; }
    public float getLight() { return this.light; }
    public float getHeating() { return this.heating; }
}
