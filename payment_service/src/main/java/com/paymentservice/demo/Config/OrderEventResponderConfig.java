package com.paymentservice.demo.Config;

import com.dtos.demo.events.OrderEvent;
import com.dtos.demo.events.PaymentEvent;
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
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> orderEventProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::paymentCheck);
    }

    private Mono<PaymentEvent> paymentCheck(OrderEvent orderEvent){
        Payment payment = paymentRepository.findById(orderEvent.getPaymentId()).get();
        PaymentState paymentState = (payment.getPaymentState().equals(PaymentState.SUCCESSFULL))
                ? PaymentState.SUCCESSFULL : PaymentState.FAILED;
        
        PaymentEvent paymentEvent = new PaymentEvent(
                orderEvent.getPaymentId(),
                orderEvent.getUserId(),     
                orderEvent.getOrderId(),      
                payment.getTotalprice(),
                paymentState);
        return Mono.fromSupplier(() -> paymentEvent);
    }

}
