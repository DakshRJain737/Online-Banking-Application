package com.App.onlineBanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentHistory {

    private String fromAccNo;
    private String toAccNo;
    private double amount;
    private Date date;
    private String result;

}
