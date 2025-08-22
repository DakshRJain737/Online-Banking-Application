package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.PaymentRequest;
import com.App.onlineBanking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/customer/{fromAccNo}/payment")
    public ResponseEntity<String> processPayment(@PathVariable String fromAccNo,
                                                 @RequestBody PaymentRequest request){

        return service.processPayment(fromAccNo,
                                      request.getToAccNo(),
                                      request.getAmount(),
                                      request.getDate());

    }
}
