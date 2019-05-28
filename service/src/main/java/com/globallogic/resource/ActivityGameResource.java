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
    private ActivityGameServiceImpl gameService;

    @PostMapping("/{boardSize}")
    public GameStatus<OneDimensionalCoordinate> initGame(@PathVariable int boardSize, @RequestBody List<PlayerCreateDto> playerCreateDto) throws ActivityGameException {
        return gameService.initGame(boardSize, playerCreateDto);
    }

    @GetMapping("/card/point/{point}/boardId/{boardId}")
    public Card getCard(@PathVariable int point ,@PathVariable int boardId) {
        return gameService.getCard(point, boardId);
    }

    @GetMapping("/correctAnswer/{boardId}")
    public GameStatus<OneDimensionalCoordinate> correctAnswer(@PathVariable int boardId) {
        return gameService.correctAnswer(boardId);
    }

    @GetMapping("/wrongAnswer/{boardId}")
    public GameStatus<OneDimensionalCoordinate> wrongAnswer(@PathVariable int boardId) {
        return gameService.wrongAnswer(boardId);
    }

    @GetMapping("/stats/{boardId}")
    public Statistic getAnswerStatistic(@PathVariable int boardId) {
        return gameService.getStatistic(boardId);
    }

    @ExceptionHandler(ActivityGameException.class)
    public ResponseEntity handleActivityGameException(ActivityGameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
