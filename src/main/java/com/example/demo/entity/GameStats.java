package com.example.demo.entity;

public abstract class GameStats {
    private int timePlayed;
    public GameStats(int timePlayed){
        this.timePlayed = timePlayed;
    }
    public GameStats(){}
}