package com.globallogic.activitygame.dictionary;


import com.globallogic.activitygame.coordinate.OneDimensionalCoordinate;

import java.util.*;

public class CardGenerator {


    private Map<Integer,Set<Card>> cards = new HashMap<>();

    public Map<Integer, Set<Card>> generateCards() {
        DictionaryImpl dictionary = new DictionaryImpl();
        createCard(dictionary.getOnePointsWords(),1);
        createCard(dictionary.getTwoPointsWords(),2);
        createCard(dictionary.getThreePointsWords(),3);
        return cards;
    }

    private void createCard(List<String> words, int points) {
        Set<Card> cardSet = new HashSet<>();
        for (String word : words) {
            OneDimensionalCoordinate oneDimensionalCoordinate = new OneDimensionalCoordinate(points);
            oneDimensionalCoordinate.setX(points);
            Card card = new Card(word,oneDimensionalCoordinate);
            cardSet.add(card);
        }
        cards.put(points,cardSet);
    }

}
