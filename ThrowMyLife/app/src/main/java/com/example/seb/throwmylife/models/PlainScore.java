package com.example.seb.throwmylife.models;

import com.google.gson.annotations.SerializedName;

public class PlainScore {

    @SerializedName("playerName")
    private String playerName;

    @SerializedName("score")
    private int score;

    @SerializedName("date")
    private int date;

    public PlainScore(String playerName, int score, int date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PlainScore{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                ", date=" + date +
                '}';
    }
}
