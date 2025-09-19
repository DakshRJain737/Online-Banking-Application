package com.App.onlineBanking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromAccNo;
    private String toAccNo;
    private double amount;
    private LocalDateTime date;
    private String result;

    @ManyToOne
    @JoinColumn(name = "account_id" , nullable = false)  // foreign key in payment_history table
    private Account account;

    public PaymentHistory(String fromAccNo, String toAccNo, double amount, LocalDateTime date, String result) {
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
        this.date = date;
        this.result = result;
    }

}
