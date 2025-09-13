package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.model.UserDetails;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  class UsersDetailsService {

    @Autowired
    private CustomerRepo repo;

    public ResponseEntity<?> getUserDetails(String pan) {

        Optional<Customer> customer = repo.findByPan(pan);

        if (customer.isEmpty()) {

            return new ResponseEntity<>("No Account found ", HttpStatus.NOT_FOUND);
        } else {

            UserDetails data = new UserDetails(
                    customer.get().getCustomerName(),
                    customer.get().getPhoneNumber(),
                    customer.get().getPan(),
                    customer.get().getAddress(),
                    customer.get().getPincode(),
                    customer.get().getEmail(),
                    customer.get().getBirthDate(),
                    customer.get().getAccount().getAccountNumber(),
                    customer.get().getAccount().getAccountType(),
                    customer.get().getAccount().getInterestRate(),
                    customer.get().getAccount().getBranchName()
            );

            return new ResponseEntity<>(data, HttpStatus.OK);
        }

    }
}
