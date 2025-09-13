package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.UserPaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPaymentHistoryController {

    @Autowired
    private UserPaymentHistoryService service;

    @GetMapping("/customer/{pan}/getPaymentHistory/{noOfPayments}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable String pan,
                                               @PathVariable int noOfPayments){

        return service.getPaymentHistory(pan,noOfPayments);

    }
}
