package com.globallogic.activitygame.field;

import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;
import com.globallogic.activitygame.piece.Piece;

import java.util.Set;

public class MatrixField extends Field<TwoDimensionalCoordinate> {


    public MatrixField(FieldType fieldType) {
        super(fieldType);
    }

    @Override
    public TwoDimensionalCoordinate getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(TwoDimensionalCoordinate position) {
        super.setPosition(position);
    }

    @Override
    public Set<Piece> getPieces() {
        return super.getPieces();
    }

    @Override
    public void setPieces(Set<Piece> pieces) {
        super.setPieces(pieces);
    }

    @Override
    public FieldType getFieldType() {
        return super.getFieldType();
    }

    @Override
    public void setFieldType(FieldType fieldType) {
        super.setFieldType(fieldType);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

}
