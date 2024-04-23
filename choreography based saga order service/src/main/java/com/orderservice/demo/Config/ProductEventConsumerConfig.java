package com.orderservice.demo.Config;

import com.orderservice.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ProductEventConsumerConfig {
    @Autowired
    private OrderService orderService;
    @Bean
    public Consumer<ProductEvent> productEventConsumer(){
        return (productEvn) -> orderService.updateOrde(productEvn);
    }

}
