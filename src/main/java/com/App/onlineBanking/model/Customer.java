package com.App.onlineBanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("pan")
    @Column(unique = true)
    private String pan;

    @JsonProperty("password")
    private String password;

    //After registering
    private String address;
    private String pincode;
    private String email;

    @JsonProperty("birthday")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    //Account Details
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

}