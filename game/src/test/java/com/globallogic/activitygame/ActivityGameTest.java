package com.globallogic.activitygame;

import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.PlayerCreateDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActivityGameTest {

    private ActivityGame activityGame;
    private List<PlayerCreateDto> playerCreateDtoList = new ArrayList<>();

    @Before
    public void setUp() {
        activityGame = new ActivityGame();
        PlayerCreateDto playerCreateDto = new PlayerCreateDto("First", "blue");
        PlayerCreateDto playerCreateDto2 = new PlayerCreateDto("Second", "red");
        PlayerCreateDto playerCreateDto3 = new PlayerCreateDto("Third", "black");
        PlayerCreateDto playerCreateDto4 = new PlayerCreateDto("Four", "green");
        playerCreateDtoList.add(playerCreateDto);
        playerCreateDtoList.add(playerCreateDto2);
        playerCreateDtoList.add(playerCreateDto3);
        playerCreateDtoList.add(playerCreateDto4);
        activityGame.init(30, playerCreateDtoList, 1);
        activityGame.cardByPoint(3, 1);

    }

    @Test
    public void init() {
        GameStatus<OneDimensionalCoordinate> gameStatus = activityGame.init(30, playerCreateDtoList, 1);

        assertFalse(gameStatus.isEndGame());
        assertEquals(gameStatus.getBoard().getPlayers().size(), playerCreateDtoList.size());
        assertEquals(gameStatus.getBoard().getFields().size(), 31);
        assertEquals(gameStatus.getBoard().getFields().get(0).getPieces().size(), playerCreateDtoList.size());
        assertEquals(gameStatus.getCurrentPlayer(), 1);
    }

    @Test
    public void cardByPoint() {
        Card card = activityGame.cardByPoint(3, 1);
        OneDimensionalCoordinate coordinate = new OneDimensionalCoordinate(3);
        assertEquals(card.getCoordinate().getX(), coordinate.getX());
    }

    @Test
    public void applyMovement() {
        GameStatus<OneDimensionalCoordinate> gameStatus = activityGame.applyMovement(1);
        Assert.assertEquals(gameStatus.getBoard().getFields().get(3).getPieces().size(), 1);
    }

    @Test
    public void nextPlayer() {
        GameStatus gameStatus = activityGame.wrongAnswer(1);
        Assert.assertEquals(gameStatus.getCurrentPlayer(), 2);
    }


}