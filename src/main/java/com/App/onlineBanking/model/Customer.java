package com.App.onlineBanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    @NotBlank(message = "Name must be entered")
    private String customerName;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number must be entered")
    private String phoneNumber;

    @JsonProperty("pan")
    @Column(unique = true)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format")
    @NotBlank(message = "Pan must be entered")
    private String pan;

    @JsonProperty("password")
    @NotBlank(message = "Password must be entered")
    private String password;

    //After registering
    @NotBlank(message = "Address must be entered")
    private String address;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pin-code must be 6-digit number")
    @NotBlank(message = "Pin-code must be entered")
    private String pincode;

    @Email
    @NotBlank(message = "E-mail must be entered")
    private String email;

    @JsonProperty("birthday")
    @Temporal(TemporalType.DATE)
    @NotBlank(message = "Birthdate is mandatory")
    private Date birthDate;

    //Account Details
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

}