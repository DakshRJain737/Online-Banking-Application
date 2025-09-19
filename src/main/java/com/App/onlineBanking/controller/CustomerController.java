package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<String> registration(@Valid @RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return service.registration(customer);

    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> completeProfile(@Valid @RequestBody Customer updateCustomer,
                                                  Authentication authentication){
        String pan = authentication.getName();
        return service.updateProfile(pan,updateCustomer);
    }

}
