package com.orderservice.demo.Entities;

import com.dtos.demo.events.OrderState;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data must be defined")
    private Date dateReceived;

    @NotNull(message = "Price of the order must be defined")
    private double price;

    @NotNull(message = "Description must be defined")
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