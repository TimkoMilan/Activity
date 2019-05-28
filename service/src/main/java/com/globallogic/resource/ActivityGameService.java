package com.globallogic.resource;

import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.statistic.Statistic;

import java.util.List;

public interface ActivityGameService {

    GameStatus<OneDimensionalCoordinate> initGame(int boardSize, List<PlayerCreateDto> players);

    GameStatus<OneDimensionalCoordinate> correctAnswer(int boardId);

    GameStatus<OneDimensionalCoordinate> wrongAnswer(int boardId);

    Statistic getStatistic(int boardId);


}
