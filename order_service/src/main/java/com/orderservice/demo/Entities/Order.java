package com.orderservice.demo.Entities;

import com.dtos.demo.events.OrderState;


import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OrderTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long OrderId;


    @NotNull(message = "Quantity must be defined")

    int Quantity;
    @NotNull(message = "OrderStatus must be defined")

    OrderState OrderStatus;
    @NotNull(message = "TotalPrice must be defined")

    double TotalPrice;
    @NotNull(message = "Date must be defined")

    Date Date;
    public Order() {

    }
    public Order(Long orderId,  int quantity, OrderState orderStatus, double totalPrice) {
        OrderId = orderId;
        Quantity = quantity;
        OrderStatus = orderStatus;
        TotalPrice = totalPrice;
    }
    public Order(long orderId, int quantity) {
        OrderId = orderId;
    }
    public Order(long orderId, Date date, OrderState orderStatus) {
        OrderId = orderId;
        Date = date;
        OrderStatus=orderStatus;
    }

    public Long getOrderId() {
        return OrderId;
    }


    public int getQuantity() {
        return Quantity;
    }

    public OrderState getOrderStatus() {
        return OrderStatus;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }


    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setOrderStatus(OrderState orderStatus) {
        OrderStatus = orderStatus;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "OrderId=" + OrderId +
                ", Quantity=" + Quantity +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
