package com.example.demo.dao;

import com.example.demo.entity.GameStats;
import com.example.demo.entity.KillerStats;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KillerDAOImp implements GameDAO{
    private EntityManager entityManager;
    @Autowired
    public KillerDAOImp (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<? extends GameStats> findAll() {
        TypedQuery<? extends GameStats> theQuery = entityManager.createQuery("SELECT e FROM KillerStats e", KillerStats.class);

        return theQuery.getResultList();
    }

    @Override
    public GameStats findByDay(int day) {
        return entityManager.find(KillerStats.class, day);
    }

    @Override
    public GameStats add(GameStats theStats) {
        return entityManager.merge(theStats);
    }

    @Override
    public void deleteByDay(int day) {
        entityManager.remove(entityManager.find(KillerStats.class, day));
    }

    @Override
    public KillerStats update(GameStats theGameStats) {
        KillerStats theKillerStats =(KillerStats) theGameStats;

        Optional<KillerStats> optional = Optional.ofNullable(entityManager.find(KillerStats.class, theKillerStats.getId()));
        if(optional.isEmpty()){
            throw new RuntimeException("You didn't played till " + theKillerStats.getId() + "th day");
        }

        return entityManager.merge(theKillerStats);
    }

    @Override
    public String deleteAll() {
        Query theQuery = entityManager.createQuery("DELETE FROM KillerStats");

        int rowsDeleted = theQuery.executeUpdate();
        return rowsDeleted + " rows had been deleted";
    }
}
