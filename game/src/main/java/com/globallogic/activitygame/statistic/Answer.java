package com.globallogic.activitygame.statistic;

import com.globallogic.activitygame.dictionary.Card;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {

    private Card card;
    private int playerId;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "card=" + card +
                ", playerId=" + playerId +
                '}';
    }
}
