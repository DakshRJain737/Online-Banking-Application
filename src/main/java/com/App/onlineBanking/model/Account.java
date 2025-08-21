package com.App.onlineBanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("account_number")
    @Column(unique = true)
    private String accountNumber;

    @JsonProperty("account_type")  // e.g., Savings, Current
    private String accountType;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("interest_rate")
    private double interestRate;

    @JsonProperty("branch_name")
    private String branchName;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
