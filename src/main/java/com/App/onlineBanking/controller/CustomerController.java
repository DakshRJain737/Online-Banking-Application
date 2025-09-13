package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/customer/register")
    public ResponseEntity<String> registration(@Valid @RequestBody Customer customer){

        return service.registration(customer);

    }

    @PostMapping("/customer/{pan}/updateProfile")
    public ResponseEntity<String> completeProfile(@Valid @PathVariable String pan,
                                                  @Valid @RequestBody Customer updateCustomer){

        return service.updateProfile(pan , updateCustomer);

    }

}
