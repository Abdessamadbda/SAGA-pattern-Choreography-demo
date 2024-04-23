package com.orderservice.demo.Repositories;

public class OrderRepository {
    Order save(Order order);
    Order findByOrderId(Long orderId);
    List<Order> findAll();
    void delete(Order order);
    void deleteByOrderId(Long orderId);
    void deleteAll();
}
