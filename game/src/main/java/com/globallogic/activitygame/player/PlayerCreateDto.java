package com.globallogic.activitygame.player;

import java.io.Serializable;

public class PlayerCreateDto implements Serializable {

    private String name;
    private String color;


    public PlayerCreateDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerCreateDto{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
