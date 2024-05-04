package com.orderservice.demo.Config;

import com.dtos.demo.events.OrderEvent;
import com.dtos.demo.events.AnotherOrderEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


import java.util.function.Supplier;

@Configuration
public class AnotherOrderPublisherKafkaConfig {
    // Order events will be sent to all the subscribers
    @Bean
    public Sinks.Many<AnotherOrderEvent> anotherorderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }
    // Order event supplier (We will supply multiple order events)
    @Bean
    public Supplier<Flux<AnotherOrderEvent>> anotherorderSupplier(Sinks.Many<AnotherOrderEvent> sinks){
        return sinks::asFlux;
    }
}

