package com.globallogic.resource;

import com.globallogic.activitygame.ActivityGame;
import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.exception.ActivityGameException;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.statistic.Statistic;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        int gameId = generateGameId();
        gameByGameId.put(gameId, activityGame);
        return activityGame.init(boardSize, players, gameId);
    }

    public Card getCard(int point, int gameId) {
        if (getGameById(gameId) != null) {
            if (point <= 3) {
                ActivityGame activityGame = getGameById(gameId);
                return activityGame.cardByPoint(point, gameId);
            } else {
                throw new ActivityGameException("Invalid card value");
            }
        } else {
            throw new ActivityGameException("Invalid gameId");
        }
    }

    public GameStatus<OneDimensionalCoordinate> correctAnswer(int gameId) {
        if (getGameById(gameId) != null) {
            if (checkTimeExpiration(getGameById(gameId).getGameStatus())) {
                ActivityGame activityGame = getGameById(gameId);
                GameStatus<OneDimensionalCoordinate> gameStatus = activityGame.applyMovement(gameId);
                if (gameStatus.isEndGame()) {
                    removeGame(gameId);
                }
                return gameStatus;
            } else {
                throw new ActivityGameException("Time has been expired");
            }
        } else {
            throw new ActivityGameException("Invalid gameId");
        }
    }

    public GameStatus<OneDimensionalCoordinate> wrongAnswer(int gameId) {
        if (getGameById(gameId) != null) {
            ActivityGame activityGame = getGameById(gameId);
            return activityGame.wrongAnswer(gameId);
        } else {
            throw new ActivityGameException("Invalid gameId");
        }
    }

    public Statistic getStatistic(int gameId) {
        if (getGameById(gameId) != null) {
            ActivityGame activityGame = getGameById(gameId);
            return activityGame.getStatistic(gameId);
        } else {
            throw new ActivityGameException("Invalid gameId");
        }
    }

    private int generateGameId() {
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

    private void removeGame(int gameId) {
        gameByGameId.remove(gameId);
    }

    private boolean checkTimeExpiration(GameStatus gameStatus) {
        Date currentTime = new Date();
        return currentTime.compareTo(gameStatus.getExpirationCardTime()) < 0;
    }

}
