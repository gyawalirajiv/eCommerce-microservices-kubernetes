package com.example.creditcardservice.repository;

import com.example.creditcardservice.entities.Creditcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CreditcardRepository extends JpaRepository<Creditcard, Long> {
    Optional<Creditcard> findCreditcardByCardNumberAndExpiryDateAndCcv(String cardNumber, LocalDate expiryDate, String ccv);
}
