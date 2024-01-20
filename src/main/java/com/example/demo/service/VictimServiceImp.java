package com.example.demo.service;

import com.example.demo.dao.GameDAO;
import com.example.demo.dao.VictimDAOImp;
import com.example.demo.entity.GameStats;
import com.example.demo.entity.VictimStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class VictimServiceImp implements GameService{
    private GameDAO gameDAO;
    @Autowired
    public VictimServiceImp(@Qualifier("victimDAOImp") GameDAO gameDAO){
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
    public String findPeakPerformance() {      // best performance is the highest wins / timePlayed ratio
        List<VictimStats> stats =(List<VictimStats>) gameDAO.findAll();
        VictimStats thePeakPerformance = null;
        double ratio = 0;

        for(VictimStats victimStats : stats){
            if((double) victimStats.getWin() / victimStats.getTimePlayed() > ratio ){
                ratio = (double) victimStats.getWin() / victimStats.getTimePlayed();
                thePeakPerformance = victimStats;
            }
        }

        return "The peak performance had place on this day : " + thePeakPerformance.getId() + "\nThe stats :" + thePeakPerformance.toString();
    }
}












