package com.globallogic.activitygame.statistic;

import java.io.Serializable;
import java.util.Set;

public class Statistic implements Serializable {

    private int gameId;
    private Set<Answer> correctAnswers;
    private Set<Answer> wrongAnswers;


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Set<Answer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Set<Answer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Set<Answer> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(Set<Answer> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "gameId=" + gameId +
                ", correctAnswers=" + correctAnswers +
                ", wrongAnswers=" + wrongAnswers +
                '}';
    }
}
