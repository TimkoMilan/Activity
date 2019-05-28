package com.globallogic.activitygame.dictionary;


import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;

public class Card {


    private String word;
    private OneDimensionalCoordinate coordinate;


    public Card(String word, OneDimensionalCoordinate coordinate) {
        this.word = word;
        this.coordinate = coordinate;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCoordinate(OneDimensionalCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getWord() {
        return word;
    }

    public OneDimensionalCoordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "word='" + word + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
