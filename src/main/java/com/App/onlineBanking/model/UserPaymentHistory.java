package com.App.onlineBanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentHistory {

    private String fromAccNo;
    private String toAccNo;
    private double amount;
    private LocalDateTime date;
    private String result;

}
