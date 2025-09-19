package com.App.onlineBanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private String toAccNo;
    private double amount;
    private LocalDateTime date;
}
