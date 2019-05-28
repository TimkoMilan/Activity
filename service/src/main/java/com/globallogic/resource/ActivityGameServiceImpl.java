package com.globallogic.resource;

import com.globallogic.activitygame.ActivityGame;
import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.statistic.Statistic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
class ActivityGameServiceImpl implements ActivityGameService {

    private Random randomGenerator = new Random();
    private Map<Integer, ActivityGame> gameByGameId = new ConcurrentHashMap<>();


    public GameStatus<OneDimensionalCoordinate> initGame(int boardSize, List<PlayerCreateDto> players) {
        ActivityGame activityGame = new ActivityGame();
        int gameId = generateBoardId();
        gameByGameId.put(gameId, activityGame);
        return activityGame.init(boardSize, players, gameId);
    }

    Card getCard(int point, int boardId) {
        ActivityGame activityGame = getGameById(boardId);
        return activityGame.cardByPoint(point, boardId);
    }

    public GameStatus<OneDimensionalCoordinate> correctAnswer(int boardId) {
        ActivityGame activityGame = getGameById(boardId);
        GameStatus<OneDimensionalCoordinate> gameStatus = activityGame.applyMovement(boardId);
        if (gameStatus.isEndGame()) {
            removeBoard(boardId);
        }
        return gameStatus;
    }

    public GameStatus<OneDimensionalCoordinate> wrongAnswer(int boardId) {
        ActivityGame activityGame = getGameById(boardId);
        return activityGame.wrongAnswer(boardId);
    }

   public Statistic getStatistic(int boardId) {
        ActivityGame activityGame = getGameById(boardId);
        return activityGame.getStatistic(boardId);
    }

    private int generateBoardId() {
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(100);
        } while (!ifGameWithIdExist(randomNumber));
        return randomNumber;
    }

    private boolean ifGameWithIdExist(int id) {
        return gameByGameId.get(id) == null;
    }

    private ActivityGame getGameById(int gameId) {
        return gameByGameId.get(gameId);
    }

    private void removeBoard(int boardId) {
        gameByGameId.remove(boardId);
    }
}
