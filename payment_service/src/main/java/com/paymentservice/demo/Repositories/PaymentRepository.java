package com.productservice.demo.Repositories;

import com.productservice.demo.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Product, Long> {
}
