package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService service;

    @GetMapping("/getCustomers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> allCustomers = service.getAllCustomers();
        if(allCustomers != null && !allCustomers.isEmpty()){
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createAdmin(@RequestBody Customer customer){
        service.createAdmin(customer);
    }
}
