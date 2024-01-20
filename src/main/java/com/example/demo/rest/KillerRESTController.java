package com.example.demo.rest;

import com.example.demo.entity.KillerStats;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/killer")
public class KillerRESTController {
    private GameService gameService;
    @Autowired
    public KillerRESTController(@Qualifier("killerServiceImp") GameService gameService){
        this.gameService = gameService;
    }
    @GetMapping("/stats")
    public List<KillerStats> getStats(){
        return (List<KillerStats>) gameService.findAll();
    }
    @GetMapping("/stats/{day}")
    public KillerStats getStatsByDay(@PathVariable("day") int day){
        return (KillerStats) gameService.findByDay(day);
    }
    @GetMapping("/best-performance")
    public String getBestPerformance(){
        return gameService.findPeakPerformance();
    }
    @PostMapping("/stats")
    public KillerStats addKillerStats(@RequestBody KillerStats killerStats){
        return (KillerStats) gameService.add(killerStats);
    }
    @PutMapping("/stats")
    public KillerStats updateKillerStats(@RequestBody KillerStats killerStats){
        return (KillerStats) gameService.update(killerStats);
    }
    @DeleteMapping("/stats/{day}")
    public void deleteByDay(@PathVariable("day") int day){
        gameService.deleteByDay(day);
    }
    @DeleteMapping("/stats")
    public String deleteAll(){
        return gameService.deleteAll();
    }
}















