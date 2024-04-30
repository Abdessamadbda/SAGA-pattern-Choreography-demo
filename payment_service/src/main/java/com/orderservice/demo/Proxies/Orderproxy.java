package com.orderservice.demo.Proxies;
import com.orderservice.demo.Entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "choreography-SAGA-order-service")
public interface Orderproxy {

    @GetMapping("/{orderId}")
    public Order getOrderByid(@PathVariable long orderId);

}
