package com.App.onlineBanking.service;

import com.App.onlineBanking.model.Customer;
import com.App.onlineBanking.model.PaymentHistory;
import com.App.onlineBanking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private CustomerRepo repo;

    public ResponseEntity<String> processPayment(String fromAccNo,
                                                 String toAccNo,
                                                 double amount,
                                                 LocalDateTime date) {

        Optional<Customer> fromCustomer = repo.findByAccountNumber(fromAccNo);
        Optional<Customer> toCustomer = repo.findByAccountNumber(toAccNo);

        if(toCustomer.isEmpty()){
            return new ResponseEntity<>(toAccNo + "does not exists", HttpStatus.NOT_FOUND);
        }

        if(fromCustomer.isEmpty()){
            return new ResponseEntity<>(fromAccNo + "does not exists", HttpStatus.NOT_FOUND);
        }

        double balanceOfFromCustomer = fromCustomer.get().getAccount().getBalance();

        if(balanceOfFromCustomer>=amount){

            fromCustomer
                    .get()
                    .getAccount()
                    .setBalance(fromCustomer.get().getAccount().getBalance()-amount);
            toCustomer
                    .get()
                    .getAccount()
                    .setBalance(toCustomer.get().getAccount().getBalance()+amount);

            PaymentHistory history1 = new PaymentHistory(
                    fromAccNo, toAccNo, amount, date, "Success"
            );
            history1.setAccount(fromCustomer.get().getAccount());
            fromCustomer.get().getAccount().getHistoryList().add(history1);

            PaymentHistory history2 = new PaymentHistory(
                    fromAccNo, toAccNo, amount, date, "Success"
            );
            history2.setAccount(toCustomer.get().getAccount());
            toCustomer.get().getAccount().getHistoryList().add(history2);


            return new ResponseEntity<>("Amount "+amount+" is transferred from "+fromAccNo+
                                            " to "+toAccNo,HttpStatus.ACCEPTED);

        }
        else{

            PaymentHistory history1 = new PaymentHistory(
                    fromAccNo, toAccNo, 0.00, date, "Failed"
            );
            history1.setAccount(fromCustomer.get().getAccount());
            fromCustomer.get().getAccount().getHistoryList().add(history1);

//       Not necessary because if fromCustomer has less balance it should not reflect in toCustomer
//            PaymentHistory history2 = new PaymentHistory(
//                    fromAccNo, toAccNo, 0.00, date, "Failed"
//            );
//            history2.setAccount(toCustomer.get().getAccount());
//            toCustomer.get().getAccount().getHistoryList().add(history2);

            return new ResponseEntity<>("Payment failed",HttpStatus.EXPECTATION_FAILED);
        }

    }
}
