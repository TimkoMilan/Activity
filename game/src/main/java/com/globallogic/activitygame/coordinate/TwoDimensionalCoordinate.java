package com.globallogic.activitygame.coordinate;

public class TwoDimensionalCoordinate extends Coordinate {

    private int y;

    public TwoDimensionalCoordinate(int x, int y) {
        super(x);
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }


    @Override
    public String toString() {
        return "TwoDimensionalCoordinate{" +
                "y=" + y +
                ", x=" + getX() +

                '}';
    }
}
