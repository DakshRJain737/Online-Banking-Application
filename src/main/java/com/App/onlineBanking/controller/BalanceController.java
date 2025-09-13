package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    private BalanceService service;

    @GetMapping("/customer/{accNo}/checkBalance")
    public ResponseEntity<Double> checkBalance(@PathVariable("accNo") String accNo){
        return service.checkBalance(accNo);
    }
}
