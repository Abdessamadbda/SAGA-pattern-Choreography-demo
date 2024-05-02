package com.paymentservice.demo.Config;

import com.dtos.demo.events.PaymentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class PaymentPublisherKafkaConfig {
    // Order events will be sent to all the subscribers
    @Bean
    public Sinks.Many<PaymentEvent> paymentSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }
    // Order event supplier (We will supply multiple order events)
    @Bean
    public Supplier<Flux<PaymentEvent>> paymentSupplier(Sinks.Many<PaymentEvent> sinks){
        return sinks::asFlux;
    }
}

