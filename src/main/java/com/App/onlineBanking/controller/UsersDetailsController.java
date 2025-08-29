package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersDetailsController {

    @Autowired
    private UsersDetailsService service;

    @GetMapping("/customer/{pan}/userDetails")
    public ResponseEntity<?> getUserDetails(@PathVariable String pan){

        return service.getUserDetails(pan);
    }
}
