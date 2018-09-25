package com.cricket.cricketOverSimulator.utils;

import com.cricket.cricketOverSimulator.model.Over;
import com.cricket.cricketOverSimulator.model.Scores;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Scores> {
    @Override
    public int compare(Scores s1, Scores s2) {
        return s2.getScore().compareTo(s1.getScore());
    }
}
