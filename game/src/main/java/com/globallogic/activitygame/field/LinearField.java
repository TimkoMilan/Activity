package com.globallogic.activitygame.field;


import com.globallogic.activitygame.piece.Piece;

import java.util.Set;

public class LinearField extends Field<Integer> {



    @Override
    public Integer getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(Integer position) {
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

    public LinearField(FieldType fieldType) {
        super(fieldType);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
