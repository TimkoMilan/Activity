package com.globallogic.activitygame.board;

import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.field.Field;
import com.globallogic.activitygame.piece.Piece;
import com.globallogic.activitygame.piece.PieceGenerator;
import com.globallogic.activitygame.player.Player;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class Board<T extends Coordinate> implements Serializable {

    private int id;
    private List<Field<T>> fields;
    private List<Player> players;
    private Integer currentPlayer = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Field<T>> getFields() {
        return fields;
    }

    public void setFields(List<Field<T>> fields) {
        this.fields = fields;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Integer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", fields=" + fields +
                ", players=" + players +
                ", currentPlayer=" + currentPlayer +
                '}';
    }

    public Board<T> initialize(int countOfPiece, List<Player> players, List<Field<T>> fieldList) {
        Board<T> board = new Board<>();
        PieceGenerator pieceGenerator = new PieceGenerator();
        Set<Piece> pieces;
        board.setFields(fieldList);
        pieces = pieceGenerator.generatePieces(countOfPiece, players);
        board.getFields().get(0).setPieces(pieces);

        return board;
    }

}