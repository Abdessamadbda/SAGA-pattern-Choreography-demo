package com.orderservice.demo.Controller;

import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/new/{prodId}/{qnt}")
    public Order createOrder(@PathVariable long prodId, @PathVariable int qnt){
// saveOrder of the order service
        return orderService.saveOrderInDB(prodId, qnt);
    }
}
