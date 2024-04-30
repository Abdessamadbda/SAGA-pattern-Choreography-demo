package com.orderservice.demo.Repositories;

import com.orderservice.demo.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}