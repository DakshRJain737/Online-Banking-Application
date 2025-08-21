package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.service.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRegistrationController {

    @Autowired
    private CustomerRegistrationService service;

    @PostMapping("/customer/register")
    public ResponseEntity<String> registration(@RequestBody Customer customer){

        return service.registration(customer);

    }

    @PostMapping("/customer/{pan}/updateProfile")
    public ResponseEntity<String> completeProfile(@PathVariable String pan,
                                                  @RequestBody Customer updateCustomer){

        return service.updateProfile(pan , updateCustomer);

    }

}
