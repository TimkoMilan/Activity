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
            if (cardByBoardId.get(gameId) == null && cardMap != null) {
                Set<Card> cardSet = cardMap.get(points);
                List<Card> cards = new ArrayList<>(cardSet);
                int randomNumber = random.nextInt(cards.size());
                card = cards.get(randomNumber);
                cardByBoardId.put(gameId, card);
            }
        } else {
            throw new ActivityGameException("Invalid Card Value");
        }
        logger.info(cardByBoardId.toString());
        return cardByBoardId.get(gameId);
    }

    public GameStatus<OneDimensionalCoordinate> applyMovement(int gameId) {
        card = cardByBoardId.get(gameId);
        if (card != null && linearMovement.checkMovement(getCurrentPlayerPosition(), getCurrentPlayerPosition() + card.getCoordinate().getX())) {
            if (piece.linearPieceMove(card.getCoordinate().getX(), board, playerPositionByPlayerId)) {
                gameStatus.setEndGame(true);
                currentGameStatus();
                endGame(gameId);
                return gameStatus;
            }
            setAnswerToCorrectAnswerMap(gameId);
            nextPlayer(gameId);
            cardByBoardId.remove(gameId);
            return gameStatus;
        } else {
            throw new ActivityGameException("Card not found");
        }
    }

    public GameStatus<OneDimensionalCoordinate> wrongAnswer(int boardId) {
        setAnswerToWrongAnswerMap(boardId);
        nextPlayer(boardId);
        return gameStatus;
    }

    public Statistic getStatistic(int boardId) {
        Statistic statistic = new Statistic();
        statistic.setBoardId(boardId);
        statistic.setCorrectAnswers(correctAnswer.get(boardId));
        statistic.setWrongAnswers(wrongAnswer.get(boardId));
        return statistic;
    }

    private void nextPlayer(int boardId) {
        piece.nextPiece(board);
        currentGameStatus();
        cardByBoardId.remove(boardId);
    }

    private void setPlayersToPositionMap(List<Player> playerList) {
        for (Player player : playerList) {
            playerPositionByPlayerId.put(player.getId(), 0);
        }
    }

    private int getCurrentPlayerPosition() {
        return playerPositionByPlayerId.get(board.getCurrentPlayer());
    }

    private void setAnswerToCorrectAnswerMap(int boardId) {
        if (correctAnswer.get(boardId) == null) {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> answers = new HashSet<>();
            answers.add(answer);
            correctAnswer.put(boardId, answers);
        } else {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> updatedAnswer = correctAnswer.get(boardId);
            updatedAnswer.add(answer);
            correctAnswer.put(boardId, updatedAnswer);
        }
    }

    private void setAnswerToWrongAnswerMap(int boardId) {
        if (wrongAnswer.get(boardId) == null) {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> answers = new HashSet<>();
            answers.add(answer);
            wrongAnswer.put(boardId, answers);
        } else {
            answer = new Answer();
            answer.setCard(card);
            answer.setPlayerId(gameStatus.getCurrentPlayer());
            Set<Answer> updatedAnswer = wrongAnswer.get(boardId);
            updatedAnswer.add(answer);
            wrongAnswer.put(boardId, updatedAnswer);
        }
    }

//    public static void main(String[] args) {
//        Random random = new Random();
//        ActivityGame game = new ActivityGame();
//        List<PlayerCreateDto> playerCreateDtoList = new ArrayList<>();
//        PlayerCreateDto playerCreateDto = new PlayerCreateDto("First", "blue");
//        PlayerCreateDto playerCreateDto2 = new PlayerCreateDto("Second", "red");
//        PlayerCreateDto playerCreateDto3 = new PlayerCreateDto("Third", "black");
//        PlayerCreateDto playerCreateDto4 = new PlayerCreateDto("Four", "green");
//
//        playerCreateDtoList.add(playerCreateDto);
//        playerCreateDtoList.add(playerCreateDto2);
//        playerCreateDtoList.add(playerCreateDto3);
//        playerCreateDtoList.add(playerCreateDto4);
//
//        game.init(30, playerCreateDtoList,1);
//
//        int randomNumberForMovement;
//        int randomNumberForPoints;
//        int correct = 0;
//        int wrong = 0;
//
//        do {
//            randomNumberForPoints = random.nextInt(3) + 1;
//            game.cardByPoint(randomNumberForPoints, 1);
//            randomNumberForMovement = random.nextInt(100);
//            if (wasCorrectAnswer(randomNumberForMovement)) {
//                game.applyMovement(1);
//                correct += 1;
//            } else {
//                game.wrongAnswer(1);
//                wrong += 1;
//            }
//        } while (!game.gameStatus.isEndGame());
//
//        logger.info(game.getStatistic(1));
//        logger.info("Correct Answer -> " + correct);
//        logger.info("Wrong Answer   -> " + wrong);
//
//    }
//
//    private static boolean wasCorrectAnswer(int randomNumberForMovement) {
//        return (randomNumberForMovement % 2) == 0;
//    }
}
