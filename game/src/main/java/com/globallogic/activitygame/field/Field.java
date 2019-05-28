package com.globallogic.activitygame.field;

import com.globallogic.activitygame.piece.Piece;

import java.util.Set;

public  class Field<T> {

    private int id;
    private FieldType fieldType;
    private Set<Piece> pieces;
    private T position;


    public T getPosition() {
        return position;
    }

    public void setPosition(T position) {
        this.position = position;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Field{" +
                " fieldType=" + fieldType +
                ", pieces=" + pieces +
                ", position=" + position +
                '}';
    }
}
