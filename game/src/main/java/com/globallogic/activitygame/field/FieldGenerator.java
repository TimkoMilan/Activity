package com.globallogic.activitygame.field;

import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;

import java.util.List;

public interface FieldGenerator<T> {

    List<T> generateFields(Coordinate size);
    List<T> generateTwoDimensionalFields(TwoDimensionalCoordinate size);



}

