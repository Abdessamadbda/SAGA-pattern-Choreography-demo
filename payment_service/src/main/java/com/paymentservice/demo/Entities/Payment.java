package com.paymentservice.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.dtos.demo.events.PaymentState;

import javax.validation.constraints.NotNull;

@Entity
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    @NotNull(message = "totalprice label must be defined")
    private double totalprice;

  
    @NotNull(message = "Description must be defined")
    private PaymentState paymentState = PaymentState.SUCCESSFULL;
    

    
    public Payment() {
    }

    public Payment(Long id, double totalprice, PaymentState paymentState) {
        this.id = id;
        this.totalprice = totalprice;
        this.paymentState = paymentState;
    }
public Payment( double totalprice, PaymentState paymentState) {
        this.totalprice = totalprice;
        this.paymentState = paymentState;
    }
    public Long getId() {
        return id;
    }

   

    public double getTotalprice() {
        return totalprice;
    }

   

    public void setId(Long id) {
        this.id = id;
    }

    

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    
    

}
