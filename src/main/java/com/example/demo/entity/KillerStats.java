package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "killer_stats")
public class KillerStats extends GameStats{
    @Column(name = "day")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int day;
    @Column(name = "time_played")
    private int timePlayed;
    @Column(name = "kills")
    private int kills;

    public KillerStats(){}
    public KillerStats(int timePlayed, int kills){
        super(timePlayed);
        this.kills = kills;
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

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    @Override
    public String toString() {
        return "KillerStats{" +
                "id=" + day +
                ", timePlayed=" + timePlayed +
                ", kills=" + kills +
                '}';
    }
}