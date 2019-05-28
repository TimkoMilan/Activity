package com.globallogic.activitygame.field;

import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;

import java.util.ArrayList;
import java.util.List;

public class MatrixFieldGenerator<T> implements FieldGenerator<T> {


    @Override
    public List<T> generateFields(Coordinate size) {
        return null;
    }

    @Override
    public List<T> generateTwoDimensionalFields(TwoDimensionalCoordinate size) {
            List<Field<T>>fields = new ArrayList<>();

            for (int i= 0 ;i<=size.getX(); i++){
                for (int j=0;j<=size.getY();j++){

                    MatrixField field = new MatrixField(FieldType.SHOW);
                    TwoDimensionalCoordinate coordinate = new TwoDimensionalCoordinate(i,j);
                    field.setPosition(coordinate);
                    fields.add((Field<T>) field);
                }
            }
            return (List<T>) fields;
        }

    }
