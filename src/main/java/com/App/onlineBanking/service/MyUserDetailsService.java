package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.model.UserPrincipal;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String pan) throws UsernameNotFoundException {

        Optional<Customer> customer = repo.findByPan(pan);
        if(customer.isEmpty()){
            throw new UsernameNotFoundException("No user found");
        }
        return new UserPrincipal(customer.get());
    }
}
