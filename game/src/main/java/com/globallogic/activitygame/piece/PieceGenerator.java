package com.globallogic.activitygame.piece;

import com.globallogic.activitygame.player.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PieceGenerator {

    public Set<Piece> generatePieces(int countOfPiece, List<Player> players) {
        Set<Piece> pieces = new HashSet<>();
        for (int i = 1; i <= countOfPiece; i++) {
            Piece piece = new Piece();
            Set<Piece> pieceSet = new HashSet<>();
            piece.setId(i);
            piece.setColor(players.get(i - 1).getColor());
            piece.setName(players.get(i - 1).getName());
            pieceSet.add(piece);
            players.get(i - 1).setPieces(pieceSet);
            pieces.add(piece);
        }
        return pieces;
    }
}
