package com.App.onlineBanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String customerName;

    private String phoneNumber;

    private String pan;


    private String address;
    private String pincode;
    private String email;

    private Date birthDate;

    private String accountNumber;

    private String accountType;

    private double interestRate;

    private String branchName;
}
