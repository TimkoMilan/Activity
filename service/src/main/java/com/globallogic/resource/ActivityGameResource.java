package com.globallogic.resource;

import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;
import com.globallogic.activitygame.dictionary.Card;
import com.globallogic.activitygame.exception.ActivityGameException;
import com.globallogic.activitygame.game.status.GameStatus;
import com.globallogic.activitygame.player.PlayerCreateDto;
import com.globallogic.activitygame.statistic.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityGameResource {

    @Autowired
    private ActivityGameService gameService;

    @PostMapping("/{boardSize}")
    public GameStatus<OneDimensionalCoordinate> initGame(@PathVariable int boardSize, @RequestBody List<PlayerCreateDto> playerCreateDto) {
        return gameService.initGame(boardSize, playerCreateDto);
    }

    @GetMapping("/card/point/{point}/gameId/{gameId}")
    public Card getCard(@PathVariable int point, @PathVariable int gameId) {
        return gameService.getCard(point, gameId);
    }

    @GetMapping("/correctAnswer/{gameId}")
    public GameStatus<OneDimensionalCoordinate> correctAnswer(@PathVariable int gameId) {
        return gameService.correctAnswer(gameId);
    }

    @GetMapping("/wrongAnswer/{gameId}")
    public GameStatus<OneDimensionalCoordinate> wrongAnswer(@PathVariable int gameId) {
        return gameService.wrongAnswer(gameId);
    }

    @GetMapping("/stats/{gameId}")
    public Statistic getAnswerStatistic(@PathVariable int gameId) {
        return gameService.getStatistic(gameId);
    }

    @GetMapping("/timer/{gameId}")
    public void timer(@PathVariable int gameId) {
        gameService.timer(gameId);
    }

    @ExceptionHandler(ActivityGameException.class)
    public ResponseEntity handleActivityGameException(ActivityGameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
