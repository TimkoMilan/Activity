package com.globallogic.activitygame.dictionary;


import java.util.Arrays;
import java.util.List;

public class DictionaryImpl {


    private List<String> onePointsWords = (Arrays.asList("area", "book", "business", "case", "child", "company", "country", "day", "eye", "fact", "family", "government", "group", "hand", "home", "study", "system", "thing", "time","dog", "horse", "cat", "house", "job", "life", "lot", "man", "money", "month", "mother", "Mr", "night", "number", "part", "people", "place", "dictionary", "work", "world", "year"
    ,"computer", "TV", "keyboard", "monitor", "point", "problem", "program", "question", "right", "room", "school", "state", "story", "student", "water", "way", "week", "woman"));
    private List<String> twoPointsWords = (Arrays.asList("dog", "horse", "cat", "house", "job", "life", "lot", "man", "money", "month", "mother", "Mr", "night", "number", "part", "people", "place", "dictionary", "work", "world", "year"));
    private List<String> threePointsWords = (Arrays.asList("computer", "TV", "keyboard", "monitor", "point", "problem", "program", "question", "right", "room", "school", "state", "story", "student", "water", "way", "week", "woman"));

    public List<String> getOnePointsWords() {
        return onePointsWords;
    }

    public List<String> getTwoPointsWords() {
        return onePointsWords;
    }

    public List<String> getThreePointsWords() {
        return onePointsWords;
    }

}
    
    
    

