package com.App.onlineBanking.repo;

import com.App.onlineBanking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByPan(String pan);

    @Query(value = "SELECT c from Customer c where c.account.accountNumber = :accountNumber")
    Optional<Customer> findByAccountNumber(@Param("accountNumber") String accNumber);

}
