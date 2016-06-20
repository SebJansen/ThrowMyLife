package com.example.seb.throwmylife.models;

public class Score {
    private int id;
    private String playerName;
    private int score;
    private int date;

    public Score() {
    }

    public Score(String player, int score) {
        this.playerName = player;
        this.score = score;
    }

    public Score(String player, int score, int date) {
        this.playerName = player;
        this.score = score;
        this.date = date;
    }

    public Score(int id, String player, int score, int date) {
        this.date = date;
        this.id = id;
        this.playerName = player;
        this.score = score;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return playerName;
    }

    public void setPlayer(String player) {
        this.playerName = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "date=" + date +
                ", id=" + id +
                ", player='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }
}
