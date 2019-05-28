package com.globallogic.activitygame.game.status;

import com.globallogic.activitygame.board.Board;
import com.globallogic.activitygame.coordinate.Coordinate;

public class GameStatus<T extends Coordinate> {

    private Board<T> board;
    private int currentPlayer;
    private boolean endGame = false;

    public Board<T> getBoard() {
        return board;
    }

    public void setBoard(Board<T> board) {
        this.board = board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "board=" + board +
                ", currentPlayer=" + currentPlayer +
                ", endGame=" + endGame +
                '}';
    }
}
