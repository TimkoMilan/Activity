package com.globallogic.activitygame;

import com.globallogic.activitygame.coordinate.Coordinate;
import com.globallogic.activitygame.coordinate.TwoDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.dictionary.CardGenerator;
import com.globallogic.activitygame.field.Field;
import com.globallogic.activitygame.field.FieldGenerator;
import com.globallogic.activitygame.field.MatrixFieldGenerator;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.movement.MatrixMovement;
import com.globallogic.activitygame.piece.Piece;
import com.globallogic.activitygame.player.Player;
import com.globallogic.activitygame.player.PlayerAssigner;
import com.globallogic.activitygame.player.PlayerCreateDto;
import org.apache.log4j.Logger;

import java.util.*;

public class TwoDimensionalActivityGame extends Game<TwoDimensionalCoordinate> {


    private final static Logger logger = Logger.getLogger(ActivityGame.class);

    private FieldGenerator<Field<TwoDimensionalCoordinate>> fieldGenerator = new MatrixFieldGenerator<>();
    private Map<Integer, Coordinate> playerPositionByPlayerId = new HashMap<>();
    private PlayerAssigner playerAssigner = new PlayerAssigner();
    private MatrixMovement matrixMovement = new MatrixMovement();
    private CardGenerator cardGenerator = new CardGenerator();
    private Map<Integer, Set<Card>> cardMap;
    private Random random = new Random();
    private Piece piece = new Piece();
    private Card card;


    public GameStatus<TwoDimensionalCoordinate> init(TwoDimensionalCoordinate coordinate, List<PlayerCreateDto> playerCreateDtoList) {
        fields = fieldGenerator.generateTwoDimensionalFields(coordinate);
        cardMap = cardGenerator.generateCards();
        List<Player> playersList = playerAssigner.assignPlayerToGame(playerCreateDtoList);
        setPlayersToPositionMap(playersList);
        init(playerCreateDtoList, playersList, fields);
        board.setCurrentPlayer(1);
        logger.info(gameStatus);
        return gameStatus;
    }

    public GameStatus<TwoDimensionalCoordinate> applyMovement(TwoDimensionalCoordinate twoDimensionalCoordinate,int boardID) {
        if (isMoveValid(twoDimensionalCoordinate)) {
            TwoDimensionalCoordinate currentCoordinate = (TwoDimensionalCoordinate) playerPositionByPlayerId.get(board.getCurrentPlayer());
            if (matrixMovement.checkMovement(currentCoordinate, twoDimensionalCoordinate)) {
                piece.matrixPieceMove(twoDimensionalCoordinate, board, playerPositionByPlayerId);
                currentGameStatus();
            }
            return gameStatus;
        }
        return gameStatus;
    }

    public Card cardByPoint(int points) {
        if ((points == 1 || points == 2 || points == 3)) {
            Set<Card> cardSet = cardMap.get(points);
            List<Card> cards = new ArrayList<>(cardSet);
            int randomNumber = random.nextInt(cards.size());
            card = cards.get(randomNumber);
        }
        return card;
    }

    private void setPlayersToPositionMap(List<Player> playerList) {
        for (Player player : playerList) {
            TwoDimensionalCoordinate coordinate = new TwoDimensionalCoordinate(0, 0);
            playerPositionByPlayerId.put(player.getId(), coordinate);
        }
    }

    private boolean isMoveValid(TwoDimensionalCoordinate coordinate) {
        Field<TwoDimensionalCoordinate> field = board.getFields().get(board.getFields().size() - 1);
        TwoDimensionalCoordinate currentCoordinate = field.getPosition();
        return currentCoordinate.getY() >= coordinate.getY() && currentCoordinate.getX() >= coordinate.getX();
    }



    @Override
    protected void endGame(int gameID) {
        super.endGame(gameID);
    }

    @Override
    protected GameStatus<TwoDimensionalCoordinate> currentGameStatus() {
        return super.currentGameStatus();
    }

    @Override
    protected GameStatus<TwoDimensionalCoordinate> applyMovement(int gameId) {
        return null;
    }


//    public static void main(String[] args) {
//
//        List<PlayerCreateDto> playerCreateDtoList = new ArrayList<>();
//        PlayerCreateDto playerCreateDto = new PlayerCreateDto("First", "blue");
//        PlayerCreateDto playerCreateDto2 = new PlayerCreateDto("Second", "brown");
//        PlayerCreateDto playerCreateDto3 = new PlayerCreateDto("Third", "black");
//        PlayerCreateDto playerCreateDto4 = new PlayerCreateDto("Four", "green");
//
//        playerCreateDtoList.add(playerCreateDto);
//        playerCreateDtoList.add(playerCreateDto2);
//        playerCreateDtoList.add(playerCreateDto3);
//        playerCreateDtoList.add(playerCreateDto4);
//
//        TwoDimensionalActivityGame twoDimensionalActivityGame = new TwoDimensionalActivityGame();
//        twoDimensionalActivityGame.init(new TwoDimensionalCoordinate(5, 5), playerCreateDtoList);
//        twoDimensionalActivityGame.cardByPoint(1);
//        twoDimensionalActivityGame.applyMovement(new TwoDimensionalCoordinate(1, 1),3);
//        twoDimensionalActivityGame.cardByPoint(2);
//        twoDimensionalActivityGame.applyMovement(new TwoDimensionalCoordinate(1, 2),3);
//        twoDimensionalActivityGame.cardByPoint(3);
//        twoDimensionalActivityGame.applyMovement(new TwoDimensionalCoordinate(3, 4),3);
//        twoDimensionalActivityGame.cardByPoint(2);
//        twoDimensionalActivityGame.applyMovement(new TwoDimensionalCoordinate(4, 4),3);
//    }
}
