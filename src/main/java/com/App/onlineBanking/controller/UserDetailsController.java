package com.App.onlineBanking.controller;

import com.App.onlineBanking.model.UserDetails;
import com.App.onlineBanking.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    @Autowired
    private UserDetailsService service;

    @GetMapping("/customer/{pan}/userDetails")
    public ResponseEntity<?> getUserDetails(@PathVariable String pan){

        return service.getUserDetails(pan);
    }
}
