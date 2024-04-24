package com.orderservice.demo.Entities;

import com.dtos.demo.events.OrderState;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long OrderId;
    Long ProductId;
    int Quantity;
    OrderState OrderStatus;
    double TotalPrice;
    Date Date;
    public Order() {

    }
    public Order(Long orderId, Long productId, int quantity, OrderState orderStatus, double totalPrice) {
        OrderId = orderId;
        ProductId = productId;
        Quantity = quantity;
        OrderStatus = orderStatus;
        TotalPrice = totalPrice;
    }
    public Order(Long orderId, Long productId, int quantity) {
        OrderId = orderId;
        ProductId = productId;
    }
    public Order(Long orderId, Date date, OrderState orderStatus) {
        OrderId = orderId;
        Date = date;
        OrderStatus=orderStatus;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public Long getProductId() {
        return ProductId;
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

    public void setProductId(Long productId) {
        ProductId = productId;
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
                ", ProductId=" + ProductId +
                ", Quantity=" + Quantity +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
