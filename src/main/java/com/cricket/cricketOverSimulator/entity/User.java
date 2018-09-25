package com.cricket.cricketOverSimulator.entity;

public class User {

    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {

        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;
}
