package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

 @Service
 public class CustomerUserDetailsService implements UserDetailsService {

     @Autowired
     private CustomerRepo repo;

     @Override
     public UserDetails loadUserByUsername(String pan) throws UsernameNotFoundException {
         Customer customer = repo.findByPan(pan)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found with PAN: " + pan));

         return org.springframework.security.core.userdetails.User
                 .withUsername(customer.getPan()) // PAN = username
                 .password(customer.getPassword()) // already encoded
                 .roles(customer.getRoles().toArray(new String[0])) // default role
                 .build();
    }
}
