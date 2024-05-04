package com.paymentservice.demo.Config;

import com.dtos.demo.events.AnotherOrderEvent;
import com.dtos.demo.events.PaymentEvent;
import com.dtos.demo.events.AnotherPaymentEvent;
import com.dtos.demo.events.PaymentState;
import com.paymentservice.demo.Entities.Payment;
import com.paymentservice.demo.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration

public class OrderEventResponderConfig {
    @Autowired
    private PaymentRepository paymentRepository;
    

    @Bean
    public Function<Flux<AnotherOrderEvent>, Flux<AnotherPaymentEvent>> anotherorderEventProcessor() {
        return anotherorderEventFlux -> anotherorderEventFlux.flatMap(this::paymentCheck);
    }

    private Mono<AnotherPaymentEvent> paymentCheck(AnotherOrderEvent orderEvent){
        Payment payment = paymentRepository.findById(orderEvent.getPaymentId()).get();
        PaymentState paymentState = (payment.getPaymentState().equals(PaymentState.SUCCESSFULL))
                ? PaymentState.SUCCESSFULL : PaymentState.FAILED;
        System.out.println(payment.getPaymentState());
        AnotherPaymentEvent paymentEvent = new AnotherPaymentEvent(
                orderEvent.getPaymentId(),
                orderEvent.getUserId(),     
                orderEvent.getOrderId(),      
                payment.getTotalprice(),
                paymentState);

        System.out.println(paymentEvent);
        return Mono.fromSupplier(() -> paymentEvent);
    }

    

}
