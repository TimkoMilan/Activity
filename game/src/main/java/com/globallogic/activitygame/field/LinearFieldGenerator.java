package com.globallogic.activitygame.field;



import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;

import java.util.ArrayList;
import java.util.List;

public class LinearFieldGenerator<T> implements FieldGenerator<T>  {

    @Override
    public List<T> generateFields(Coordinate size) {

        List<Field<Integer>> fields = new ArrayList<>();

        Field<Integer> fieldStart = new LinearField(FieldType.START);
        fieldStart.setId(1);
        fieldStart.setPosition(0);
        fields.add(fieldStart);
        for (int i = 2; i <= size.getX(); i++) {
            switch (fields.get(i - 2).getFieldType()) {
                case START: {
                    Field<Integer> fieldRegular = new LinearField(FieldType.TALK);
                    fieldRegular.setId(i);
                    fieldRegular.setPosition(i-1);
                    fields.add(fieldRegular);
                    break;
                }
                case TALK: {
                    Field<Integer> fieldRegular = new LinearField(FieldType.WRITE);
                    fieldRegular.setId(i);
                    fieldRegular.setPosition(i - 1);
                    fields.add(fieldRegular);
                    break;
                }
                case WRITE: {
                    Field<Integer> fieldRegular = new LinearField(FieldType.SHOW);
                    fieldRegular.setId(i);
                    fieldRegular.setPosition(i - 1);
                    fields.add(fieldRegular);
                    break;
                }
                case SHOW: {
                    Field<Integer> fieldRegular = new LinearField(FieldType.TALK);
                    fieldRegular.setId(i);
                    fieldRegular.setPosition(i - 1);
                    fields.add(fieldRegular);
                    break;
                }
            }
        }
        Field<Integer> fieldFinish = new LinearField(FieldType.FINISH);
        fieldFinish.setId(size.getX() + 1);
        fieldFinish.setPosition(size.getX() + 1);
        fields.add(fieldFinish);
        return (List<T>) fields;


    }

    @Override
    public List<T> generateTwoDimensionalFields(TwoDimensionalCoordinate size) {
        return null;
    }
}
