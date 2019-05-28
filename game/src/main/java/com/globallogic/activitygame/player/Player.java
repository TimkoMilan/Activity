package com.globallogic.activitygame.player;


import com.globallogic.activitygame.piece.Piece;

import java.io.Serializable;
import java.util.Set;

public class Player implements Serializable {

    private Integer id;
    private String name;
    private String color;
    private Set<Piece> pieces;


    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
