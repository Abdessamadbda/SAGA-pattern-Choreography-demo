package com.orderservice.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class OrderController {
    @GetMapping("/new/{prodId}/{qnt}")
    public Order createOrder(@PathVariable long prodId, @PathVariable int qnt){
// saveOrder of the order service
        return orderService.saveOrderInDB(prodId, qnt);
    }
}
