package com.globallogic.activitygame.game.status;

import com.globallogic.activitygame.board.Board;
import com.globallogic.activitygame.coordinate.Coordinate;

import java.util.Date;

public class GameStatus<T extends Coordinate> {

    private Board<T> board;
    private int currentPlayer;
    private boolean endGame = false;
    private Date expirationCardTime;

    public Date getExpirationCardTime() {
        return expirationCardTime;
    }

    public void setExpirationCardTime(Date expirationCardTime) {
        this.expirationCardTime = expirationCardTime;
    }

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
                ", expirationCardTime=" + expirationCardTime +
                '}';
    }
}
