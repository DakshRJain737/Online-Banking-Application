package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.UserPaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class UserPaymentHistoryController {

    @Autowired
    private UserPaymentHistoryService service;

    @GetMapping("/getPaymentHistory/{noOfPayments}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable int noOfPayments,
                                               Authentication authentication){
        String pan = authentication.getName();
        return service.getPaymentHistory(pan,noOfPayments);

    }
}
