package com.keirenw.fundamentals.fundamentals.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class PlayerHomeModel {
    @JsonBackReference
    public String world;
    @JsonBackReference
    public float x;
    @JsonBackReference
    public float y;
    @JsonBackReference
    public float z;
    @JsonBackReference
    public float yaw;
    @JsonBackReference
    public float pitch;

    public PlayerHomeModel(String world, float x, float y, float z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
