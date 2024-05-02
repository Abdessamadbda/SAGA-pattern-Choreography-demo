package com.paymentservice.demo.Config;

import com.dtos.demo.events.UserEvent;
import com.paymentservice.demo.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UserEventConsumerConfig {
    @Autowired
    private PaymentService paymentService;
    @Bean
    public Consumer<UserEvent> userEventConsumer(){
        return (userEvn) -> paymentService.updatePayment(userEvn);
    }

}
