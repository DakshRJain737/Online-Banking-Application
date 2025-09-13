package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Account;
import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.model.PaymentHistory;
import com.App.onlineBanking.model.UserPaymentHistory;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPaymentHistoryService {

    @Autowired
    private CustomerRepo repo;

    public ResponseEntity<?> getPaymentHistory(String pan,int noOfPayments) {

        Optional<Customer> optionalCustomer = repo.findByPan(pan);

        if(optionalCustomer.isEmpty()){
            return new ResponseEntity<>("No Account Found", HttpStatus.NOT_FOUND);
        }
        else{
            Customer customer = optionalCustomer.get();
            Account account = customer.getAccount();
            List<PaymentHistory> paymentHistory = account.getHistoryList()
                    .stream()
                    .sorted(Comparator.comparing(PaymentHistory::getDate).reversed())
                    .collect(Collectors.toList());

            List<UserPaymentHistory> history = new ArrayList<>();

            for (int i = 0; i < noOfPayments && i <paymentHistory.size(); i++) {
                PaymentHistory h = paymentHistory.get(i);
                UserPaymentHistory userPaymentHistory =  new UserPaymentHistory(
                        h.getFromAccNo(),
                        h.getToAccNo(),
                        h.getAmount(),
                        h.getDate(),
                        h.getResult()
                );
                history.add(userPaymentHistory);
            }

            return new ResponseEntity<>(history,HttpStatus.OK);
        }
    }
}
