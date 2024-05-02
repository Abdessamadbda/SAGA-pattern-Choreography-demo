package com.orderservice.demo.Config;

import com.dtos.demo.events.PaymentEvent;
import com.orderservice.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;

@Configuration
public class PaymentEventConsumerConfig {
    @Autowired
    private OrderService orderService;
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return (paymentEvn) -> orderService.updateOrderPayment(paymentEvn);
    }

}
