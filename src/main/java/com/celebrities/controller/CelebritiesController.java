package com.celebrities.controller;

import com.celebrities.modal.Celebrities;
import com.celebrities.service.CelebritiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app/celebrities")
public class CelebritiesController {

    @Autowired
    CelebritiesService celebritiesService;

    @GetMapping("/get")
    public ResponseEntity<List<Celebrities>> getCelebritiesDetails(){
        return new ResponseEntity<>(celebritiesService.getCelebritiesDetails(log), HttpStatus.OK);
    }

    @PutMapping("/add")
    public String addCelebrities(@RequestBody Celebrities celebrities){
        return celebritiesService.saveCelebritiesDetails(celebrities, log);
    }

    @PutMapping("/update")
    public String editCelebrities(@RequestBody Celebrities celebrities){
        return celebritiesService.updateCelebritiesDetails(celebrities, log);
    }

    @GetMapping("/bestTime")
    public String getBestTime(){
        return celebritiesService.getBestTime(log);
    }
}
