package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.model.PaymentRequest;
import com.App.onlineBanking.repo.CustomerRepo;
import com.App.onlineBanking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private CustomerRepo repo;

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request,
                                                 Authentication authentication){
        request.setDate(LocalDateTime.now());
        String pan = authentication.getName();
        Optional<Customer> customer =  repo.findByPan(pan);
        String fromAccNo;
        if(customer.isEmpty()){
            return new ResponseEntity<>("No user found" , HttpStatus.BAD_REQUEST);
        }
        else{
            fromAccNo = customer.get().getAccount().getAccountNumber();
        }
        return service.processPayment(fromAccNo,
                                      request.getToAccNo(),
                                      request.getAmount(),
                                      request.getDate());

    }
}
