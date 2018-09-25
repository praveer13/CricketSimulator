package com.cricket.cricketOverSimulator.controller;

import com.cricket.cricketOverSimulator.model.Over;
import com.cricket.cricketOverSimulator.service.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/cricket")
public class OverController {

    @Autowired
    private
    CricketService cricketService;

    @GetMapping("/ball")
    public Over playBall(
            @RequestParam(value = "user_id") String userID
        ) throws Exception {
        return cricketService.playBall(userID);
    }

    @GetMapping("/init_over")
    public String initOver(){
        return cricketService.initiateOver();
    }
}
