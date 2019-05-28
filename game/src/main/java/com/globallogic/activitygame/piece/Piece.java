package com.globallogic.activitygame.piece;


import com.globallogic.activitygame.board.Board;
import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;
import com.globallogic.activitygame.field.Field;

import java.util.*;

import org.apache.log4j.Logger;

public class Piece {

    private final static Logger logger = Logger.getLogger(Piece.class);

    private int id;
    private String name;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Piece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public boolean linearPieceMove(int increasedPosition, Board board, Map<Integer, Integer> playersPositionByPlayerId) {
        List<Field<OneDimensionalCoordinate>> fields = board.getFields();
        int fieldId = 0;
        Piece piece = null;
        for (Field<OneDimensionalCoordinate> field : fields) {
            if (null != field.getPieces()) {
                Set<Piece> pieces = field.getPieces();
                Optional<Piece> optionalPiece = pieces.stream().filter(p -> p.getId() == board.getCurrentPlayer()).findFirst();
                if (optionalPiece.isPresent()) {
                    piece = optionalPiece.get();
                    pieces.remove(optionalPiece.get());
                    fieldId = field.getId();
                }
            }
        }
        Field<OneDimensionalCoordinate> field;
        Set<Piece> updatedSetOfPiece;

        int newPosition = fieldId + increasedPosition;
        if (newPosition >= fields.size()) {
            Set<Piece> pieceInFinish = new HashSet<>();
            pieceInFinish.add(piece);
            return true;
        }
        field = (Field<OneDimensionalCoordinate>) board.getFields().get(fieldId + increasedPosition - 1);

        playersPositionByPlayerId.put(board.getCurrentPlayer(), field.getId());

        if (field.getPieces() != null) {
            updatedSetOfPiece = field.getPieces();
            updatedSetOfPiece.add(piece);
            field.setPieces(updatedSetOfPiece);
            return false;
        } else {
            Set<Piece> pieces = new HashSet<>();
            pieces.add(piece);
            field.setPieces(pieces);
            return false;
        }

    }

    public void matrixPieceMove(TwoDimensionalCoordinate coordinate, Board board,
                                Map<Integer, Coordinate> playersPositionByPlayerId) {

        List<Field<TwoDimensionalCoordinate>> fields = board.getFields();
        Piece piece = null;

        for (Field<TwoDimensionalCoordinate> field : fields) {
            if (null != field.getPieces()) {
                Set<Piece> pieces = field.getPieces();
                Optional<Piece> optionalPiece = pieces.stream().filter(p -> p.getId() == board.getCurrentPlayer()).findFirst();
                if (optionalPiece.isPresent()) {
                    piece = optionalPiece.get();
                    pieces.remove(optionalPiece.get());
                }
            }
        }

        Optional<Field<TwoDimensionalCoordinate>> optionalField = fields.stream().filter(p -> p.getPosition().getX() == coordinate.getX() && p.getPosition().getY() == coordinate.getY()).findFirst();

        Field<TwoDimensionalCoordinate> field = optionalField.get();
        Set<Piece> updatedSetOfPiece;
        if (field.getPieces() != null) {
            updatedSetOfPiece = field.getPieces();
            updatedSetOfPiece.add(piece);
            field.setPieces(updatedSetOfPiece);
        } else {
            Set<Piece> pieces = new HashSet<>();
            pieces.add(piece);
            field.setPieces(pieces);
        }
        playersPositionByPlayerId.put(board.getCurrentPlayer(), coordinate);
        nextPiece(board);

    }

    public void nextPiece(Board board) {
        if (board.getCurrentPlayer() < board.getPlayers().size()) {
            int newPosition = board.getCurrentPlayer() + 1;
            board.setCurrentPlayer(newPosition);
        } else {
            board.setCurrentPlayer(1);
        }
    }

}
