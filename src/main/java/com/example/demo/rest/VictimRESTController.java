package com.example.demo.rest;

import com.example.demo.entity.GameStats;
import com.example.demo.entity.KillerStats;
import com.example.demo.entity.VictimStats;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/victim")
public class VictimRESTController {
    private GameService gameService;
    @Autowired
    public VictimRESTController(@Qualifier("victimServiceImp")GameService gameService){
        this.gameService = gameService;
    }
    @GetMapping("/stats")
    public List<VictimStats> getStats(){
        return (List<VictimStats>) gameService.findAll();
    }
    @GetMapping("/stats/{day}")
    public VictimStats getStatsByDay(@PathVariable("day") int day){
        return (VictimStats) gameService.findByDay(day);
    }
    @GetMapping("/best-performance")
    public String getBestPerformance(){
        return gameService.findPeakPerformance();
    }
    @PostMapping("/stats")
    public VictimStats addKillerStats(@RequestBody VictimStats victimStats){
        return (VictimStats) gameService.add(victimStats);
    }
    @PutMapping("/stats")
    public VictimStats updateKillerStats(@RequestBody VictimStats victimStats){
        return (VictimStats) gameService.update(victimStats);
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
