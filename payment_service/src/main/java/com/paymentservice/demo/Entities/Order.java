package com.paymentservice.demo.Entities;

import com.dtos.demo.events.OrderState;



import java.util.Date;


public class Order {

    
    private Long id;

    private Date dateReceived;

    private double price;

    private OrderState orderState = OrderState.CREATED;

    public Order() {
    }

    public Order(double price, Date dateReceived, OrderState orederState) {
        this.price = price;
        this.dateReceived = dateReceived;
        this.orderState = orederState;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

}