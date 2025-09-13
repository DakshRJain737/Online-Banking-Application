package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Account;
import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<String> registration(Customer customer) {

        if(searchPan(customer) != null){
            return new ResponseEntity<>("User Already exists", HttpStatus.CONFLICT);
        }
        else{
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            repo.save(customer);
            return new ResponseEntity<>("Registration Success", HttpStatus.CREATED);
        }

    }

    public Customer searchPan(Customer customer) {
        Optional<Customer> customerCheck = repo.findByPan(customer.getPan());
        return customerCheck.orElse(null);
    }

    public ResponseEntity<String> updateProfile(String pan, Customer updatedCustomer) {

        Optional<Customer> existingOpt = repo.findByPan(pan);

        if (existingOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Customer existing = existingOpt.get();
        existing.setAddress(updatedCustomer.getAddress());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setBirthDate(updatedCustomer.getBirthDate());
        existing.setPincode(updatedCustomer.getPincode());

        Account account = new Account();

        account.setBalance(10000.00);
        account.setAccountNumber(generateAccountNumber());
        account.setInterestRate(3.25);
        account.setBranchName("National Branch");
        account.setAccountType(generateAccountType());

        existing.setAccount(account);
        account.setCustomer(existing);

        repo.save(existing);

        return new ResponseEntity<>("Profile Updated", HttpStatus.OK);
    }

    private String generateAccountType() {

        String[] type = {"Savings" , "Current" ,"Savings+Current"};
        int random = (int) (Math.random() * type.length);
        return type[random];

    }

    private String generateAccountNumber() {

        String accNumber;
        do {
            long number = 100_000_000_000L + (long)(Math.random() * 900_000_000_000L);
            accNumber = String.valueOf(number);
        } while (repo.findByAccountNumber(accNumber).isPresent());
        return accNumber;
    }
}






