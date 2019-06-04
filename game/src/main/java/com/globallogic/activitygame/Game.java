package com.globallogic.activitygame;

import com.globallogic.activitygame.board.Board;
import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.field.Field;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.Player;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.statistic.Answer;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Game<T extends Coordinate> {

    private final static org.apache.log4j.Logger logger = Logger.getLogger(Game.class);

    Board<T> board;
    List<Field<T>> fields;
    GameStatus<T> gameStatus;
    Map<Integer, Card> cardByGameId = new HashMap<>();


    void init(List<PlayerCreateDto> playerCreateDtoList, List<Player> players, List<Field<T>> fields) {
        gameStatus = new GameStatus<>();
        board = new Board<>();
        board.setFields(fields);
        board.setPlayers(players);
        board.initialize(playerCreateDtoList.size(), players, fields);
        gameStatus.setBoard(board);
    }


    protected void endGame(int gameID) {
        cardByGameId.remove(gameID);
    }

    protected GameStatus<T> currentGameStatus() {
        gameStatus.setBoard(board);
        gameStatus.setCurrentPlayer(board.getCurrentPlayer());
        logger.info(gameStatus);
        return gameStatus;
    }

    protected abstract GameStatus<T> applyMovement(int gameId);
}
