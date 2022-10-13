package com.example.paypalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PayPalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayPalServiceApplication.class, args);
    }

}
