package com.example.paypalservice.repository;

import com.example.paypalservice.entity.Paypal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayPalRepo extends JpaRepository<Paypal,Long> {
    Optional<Paypal> findByEmailAddressAndSecureKey(String emailAddress,String secureKey);

}
