package com.example.seb.throwmylife.models;

public class Score {
    private int id;
    private String player;
    private int score;
    private int date;

    public Score() {
    }

    public Score(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public Score(String player, int score, int date) {
        this.player = player;
        this.score = score;
        this.date = date;
    }

    public Score(int id, String player, int score, int date) {
        this.date = date;
        this.id = id;
        this.player = player;
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
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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
                ", player='" + player + '\'' +
                ", score=" + score +
                '}';
    }
}
