package com.orderservice.demo.Entities;


import com.dtos.demo.events.PaymentState;


public class Payment{

  
    private Long id;

    
    private double totalprice;

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
