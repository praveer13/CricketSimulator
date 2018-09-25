package com.cricket.cricketOverSimulator.model;

import java.util.HashSet;

public class Scores {
    public Integer getScore() {
        return score;
    }

    public Scores(Integer score) {
        this.score = score;
        this.users = new HashSet<>();
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public HashSet<String> getUsers() {
        return users;
    }

    public void setUsers(HashSet<String> users) {
        this.users = users;
    }

    private Integer score;
    private HashSet<String> users;
}
