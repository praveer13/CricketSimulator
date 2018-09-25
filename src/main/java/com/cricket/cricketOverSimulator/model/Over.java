package com.cricket.cricketOverSimulator.model;

import com.cricket.cricketOverSimulator.utils.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

public class Over {

    private String matchStatus;

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Over() {
        this.matchStatus = Constants.ACTIVE;
        this.ballsPlayed = 0;
        this.ballToRunMap = new HashMap<>();
        this.totalScore = 0;
        this.rank = null;
    }

    private int ballsPlayed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public Map<Integer, Integer> getBallToRunMap() {
        return ballToRunMap;
    }

    public void setBallToRunMap(Map<Integer, Integer> ballToRunMap) {
        this.ballToRunMap = ballToRunMap;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    private Integer totalScore;

    private Map<Integer,Integer> ballToRunMap;
}
