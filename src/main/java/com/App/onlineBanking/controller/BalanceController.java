package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class BalanceController {

    @Autowired
    private BalanceService service;

    @GetMapping("/checkBalance")
    public ResponseEntity<Double> checkBalance(Authentication authentication){
        String pan = authentication.getName();
        return service.checkBalance(pan);
    }
}
