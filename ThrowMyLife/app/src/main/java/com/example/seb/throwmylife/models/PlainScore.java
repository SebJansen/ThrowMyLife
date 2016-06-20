
package com.example.seb.throwmylife.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlainScore {

    @SerializedName("playerName")
    @Expose
    private String playerName;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("date")
    @Expose
    private int date;

    /**
     * @return The playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName The playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return The score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score The score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return The date
     */
    public int getDate() {
        return date;
    }

    /**
     * @param date The date
     */
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


    public PlainScore(String playerName, int score, int date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }
}