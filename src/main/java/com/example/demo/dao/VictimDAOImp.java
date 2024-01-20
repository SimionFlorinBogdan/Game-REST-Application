package com.example.demo.dao;

import com.example.demo.entity.GameStats;
import com.example.demo.entity.KillerStats;
import com.example.demo.entity.VictimStats;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class VictimDAOImp implements GameDAO{
    private EntityManager entityManager;
    @Autowired
    public VictimDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<? extends GameStats> findAll() {
        TypedQuery<VictimStats> theQuery = entityManager.createQuery("SELECT e FROM VictimStats e", VictimStats.class);

        return theQuery.getResultList();
    }

    @Override
    public GameStats findByDay(int day) {
        return entityManager.find(VictimStats.class, day);
    }

    @Override
    public GameStats add(GameStats theStats) {
        return entityManager.merge(theStats);
    }

    @Override
    public void deleteByDay(int day) {
        entityManager.remove(entityManager.find(VictimStats.class, day));
    }

    @Override
    public GameStats update(GameStats theGameStats) {
        VictimStats theVictimStats =(VictimStats) theGameStats;

        Optional<VictimStats> optional = Optional.ofNullable(entityManager.find(VictimStats.class, theVictimStats.getId()));

        if(optional.isEmpty()){
            throw new RuntimeException("You didn't played till " + theVictimStats.getId() +"th day");
        }

        return entityManager.merge(theVictimStats);
    }

    @Override
    public String deleteAll() {
        Query theQuery = entityManager.createQuery("DELETE FROM VictimStats");

        int rowsDeleted = theQuery.executeUpdate();
        return rowsDeleted + " rows had been deleted";
    }
}
