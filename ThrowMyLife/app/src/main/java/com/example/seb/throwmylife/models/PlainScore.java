
package com.example.seb.throwmylife.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlainScore {

    @SerializedName("playerName")
    @Expose
    private String playerName;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("location")
    @Expose
    private List<String> location;

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

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PlainScore{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                ", location=" + location +
                '}';
    }

    public PlainScore(String playerName, int score, List<String> location) {
        this.playerName = playerName;
        this.score = score;
        this.location = location;
    }
}