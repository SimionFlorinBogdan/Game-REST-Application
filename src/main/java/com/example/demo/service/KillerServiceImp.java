package com.example.demo.service;

import com.example.demo.dao.GameDAO;
import com.example.demo.entity.GameStats;
import com.example.demo.entity.KillerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class KillerServiceImp implements GameService{
    private GameDAO gameDAO;
    @Autowired
    public KillerServiceImp(@Qualifier("killerDAOImp")GameDAO gameDAO){
        this.gameDAO = gameDAO;
    }
    @Override
    public List<? extends GameStats> findAll() {
        return gameDAO.findAll();
    }

    @Override
    public GameStats findByDay(int day) {
        return gameDAO.findByDay(day);
    }

    @Transactional
    @Override
    public GameStats add(GameStats stats) {
        return gameDAO.add(stats);
    }

    @Transactional
    @Override
    public void deleteByDay(int day) {
        gameDAO.deleteByDay(day);
    }

    @Transactional
    @Override
    public GameStats update(GameStats theGameStats) {
        return gameDAO.update(theGameStats);
    }

    @Transactional
    @Override
    public String deleteAll() {
        return gameDAO.deleteAll();
    }

    @Override
    public String findPeakPerformance() {      // best performance is the highest kills / timePlayed ratio
        List<KillerStats> stats =(List<KillerStats>) gameDAO.findAll();
        KillerStats thePeakPerformance = null;
        double ratio = 0;

        for(KillerStats killerStats : stats){
            if((double) killerStats.getKills() / killerStats.getTimePlayed() > ratio){
                ratio =(double) killerStats.getKills() / killerStats.getTimePlayed();
                thePeakPerformance = killerStats;
            }
        }

        return "The peak performance had place on this day : " + thePeakPerformance.getId() + "\nThe stats :" + thePeakPerformance.toString();
    }
}












