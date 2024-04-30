package com.orderservice.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name ="payment")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double totalPrice;

    @NotNull(message = "Description must be defined")
    private PaymentState paymentState = PaymentState.CREATED;

    // Constructors, getters, and setters
    public Payment() {
    }

    public Payment(double totalPrice, PaymentState paymentState) {
        this.totalPrice = totalPrice;
        this.paymentState = paymentState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }
}
