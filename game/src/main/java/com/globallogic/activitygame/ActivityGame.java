package com.globallogic.activitygame;

import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.dictionary.CardGenerator;
import com.globallogic.activitygame.exception.ActivityGameException;
import com.globallogic.activitygame.field.Field;
import com.globallogic.activitygame.field.FieldGenerator;
import com.globallogic.activitygame.field.LinearFieldGenerator;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.movement.LinearMovement;
import com.globallogic.activitygame.piece.Piece;
import com.globallogic.activitygame.player.Player;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.player.PlayerAssigner;
import com.globallogic.activitygame.statistic.Answer;
import com.globallogic.activitygame.statistic.Statistic;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ActivityGame extends Game<OneDimensionalCoordinate> {

    private final static Logger logger = Logger.getLogger(ActivityGame.class);

    private FieldGenerator<Field<OneDimensionalCoordinate>> linearFieldGenerator = new LinearFieldGenerator<>();
    private Card card = new Card("", new OneDimensionalCoordinate(0));
    private Map<Integer, Integer> playerPositionByPlayerId = new HashMap<>();
    private Map<Integer, Set<Answer>> correctAnswer = new HashMap<>();
    private Map<Integer, Set<Answer>> wrongAnswer = new HashMap<>();
    private LinearMovement linearMovement = new LinearMovement();
    private PlayerAssigner playerAssigner = new PlayerAssigner();
    private CardGenerator cardGenerator = new CardGenerator();
    private Map<Integer, Set<Card>> cardMap;
    private Random random = new Random();
    private Piece piece = new Piece();
    private Answer answer;


    public GameStatus<OneDimensionalCoordinate> init(int boardSize, List<PlayerCreateDto> playerCreateDtoList, int gameId) throws ActivityGameException {
        Coordinate coordinate = new OneDimensionalCoordinate(boardSize);
        fields = linearFieldGenerator.generateFields(coordinate);
        cardMap = cardGenerator.generateCards();
        List<Player> playersList = playerAssigner.assignPlayerToGame(playerCreateDtoList);
        setPlayersToPositionMap(playersList);
        init(playerCreateDtoList, playersList, fields);
        board.setId(gameId);
        currentGameStatus();
        return gameStatus;
    }

    public Card cardByPoint(int points, int gameId) {
        if (points == 1 || points == 2 || points == 3) {
            if (cardByGameId.get(gameId) == null && cardMap != null) {
                Set<Card> cardSet = cardMap.get(points);
                List<Card> cards = new ArrayList<>(cardSet);
                int randomNumber = random.nextInt(cards.size());
                card = cards.get(randomNumber);
                cardByGameId.put(gameId, card);

            }
        }
        logger.info(cardByGameId.toString());
        return cardByGameId.get(gameId);
    }

    public void timer() {
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + TimeUnit.MINUTES.toMillis(1));
        gameStatus.setExpirationCardTime(expirationTime);
    }

    public GameStatus<OneDimensionalCoordinate> applyMovement(int gameId) {
        card = cardByGameId.get(gameId);
        if (card != null && linearMovement.checkMovement(getCurrentPlayerPosition(), getCurrentPlayerPosition() + card.getCoordinate().getX())) {
            if (piece.linearPieceMove(card.getCoordinate().getX(), board, playerPositionByPlayerId)) {
                gameStatus.setEndGame(true);
                currentGameStatus();
                endGame(gameId);
                gameStatus.setExpirationCardTime(null);
                return gameStatus;
            } else {
                setAnswerToCorrectAnswerMap(gameId);
                nextPlayer(gameId);
                cardByGameId.remove(gameId);
                gameStatus.setExpirationCardTime(null);
                return gameStatus;
            }
        } else {
            throw new ActivityGameException("Card not found or expired time");
        }
    }

    public GameStatus<OneDimensionalCoordinate> wrongAnswer(int gameId) {
        if (cardByGameId.get(gameId) != null) {
            setAnswerToWrongAnswerMap(gameId);
            nextPlayer(gameId);
            cardByGameId.remove(gameId);
            gameStatus.setExpirationCardTime(null);
            return gameStatus;
        } else {
            throw new ActivityGameException("Card not found ");
        }
    }

    public GameStatus<OneDimensionalCoordinate> getGameStatus() {
        return gameStatus;
    }

    public Statistic getStatistic(int gameId) {
        Statistic statistic = new Statistic();
        statistic.setBoardId(gameId);
        statistic.setCorrectAnswers(correctAnswer.get(gameId));
        statistic.setWrongAnswers(wrongAnswer.get(gameId));
        return statistic;
    }

    private void nextPlayer(int gameID) {
        piece.nextPiece(board);
        currentGameStatus();
        cardByGameId.remove(gameID);
    }

    private void setPlayersToPositionMap(List<Player> playerList) {
        for (Player player : playerList) {
            playerPositionByPlayerId.put(player.getId(), 0);
        }
    }

    private int getCurrentPlayerPosition() {
        return playerPositionByPlayerId.get(board.getCurrentPlayer());
    }

    private void setAnswerToCorrectAnswerMap(int gameId) {
        if (correctAnswer.get(gameId) == null) {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> answers = new HashSet<>();
            answers.add(answer);
            correctAnswer.put(gameId, answers);
        } else {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> updatedAnswer = correctAnswer.get(gameId);
            updatedAnswer.add(answer);
            correctAnswer.put(gameId, updatedAnswer);
        }
    }

    private void setAnswerToWrongAnswerMap(int gameId) {
        if (wrongAnswer.get(gameId) == null) {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> answers = new HashSet<>();
            answers.add(answer);
            wrongAnswer.put(gameId, answers);
        } else {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> updatedAnswer = wrongAnswer.get(gameId);
            updatedAnswer.add(answer);
            wrongAnswer.put(gameId, updatedAnswer);
        }
    }
}
