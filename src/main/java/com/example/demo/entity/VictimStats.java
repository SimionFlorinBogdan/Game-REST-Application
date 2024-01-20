package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "victim_stats")
public class VictimStats extends GameStats{
    @Column(name = "day")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int day;
    @Column(name = "time_played")
    private int timePlayed;
    @Column(name = "win")
    private int win;
    public VictimStats(){}
    public VictimStats(int timePlayed, int win){
        super(timePlayed);
        this.win = win;
    }

    public int getId() {
        return day;
    }

    public void setId(int id) {
        this.day = id;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "VictimStats{" +
                "id=" + day +
                ", timePlayed=" + timePlayed +
                ", win=" + win +
                '}';
    }
}
