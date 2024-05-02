package com.paymentservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChoreographyBasedSagaPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoreographyBasedSagaPaymentServiceApplication.class, args);
    }

}
