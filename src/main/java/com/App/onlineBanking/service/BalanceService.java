package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private CustomerRepo repo;

    public ResponseEntity<Double> checkBalance(String accNo) {

        Optional<Customer> customer = repo.findByAccountNumber(accNo);

        if (customer.isEmpty()) {
            return new ResponseEntity<>(0.00,HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(customer.get().getAccount().getBalance(),HttpStatus.OK);
        }
    }
}
