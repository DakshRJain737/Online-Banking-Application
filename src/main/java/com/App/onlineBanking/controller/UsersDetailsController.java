package com.App.onlineBanking.controller;

import com.App.onlineBanking.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class UsersDetailsController {

    @Autowired
    private UsersDetailsService service;

    @GetMapping("/userDetails")
    public ResponseEntity<?> getUserDetails(Authentication authentication){
        String pan = authentication.getName();
        return service.getUserDetails(pan);
    }
}
