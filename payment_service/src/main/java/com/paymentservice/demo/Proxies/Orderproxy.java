package com.paymentservice.demo.Proxies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.paymentservice.demo.Entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "choreography-SAGA-order-service")
public interface Orderproxy {

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable long orderId);

}
