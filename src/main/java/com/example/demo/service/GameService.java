package com.example.demo.service;

import com.example.demo.entity.GameStats;

import java.util.List;

public interface GameService {
    List<? extends GameStats> findAll();
    GameStats findByDay(int day);
    GameStats add(GameStats stats);
    void deleteByDay(int day);
    GameStats update(GameStats theGameStats);
    String deleteAll();
    String findPeakPerformance();
}
