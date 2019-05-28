package com.globallogic.activitygame.coordinate;

public  class Coordinate {

    private int x;


    public Coordinate(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                '}';
    }
}
