package com.example.demo.dao;

import com.example.demo.entity.GameStats;

import java.util.List;

public interface GameDAO {
    List<? extends GameStats> findAll();
    GameStats findByDay(int day);
    GameStats add(GameStats stats);
    void deleteByDay(int day);
    GameStats update(GameStats theGameStats);
    String deleteAll();
}
