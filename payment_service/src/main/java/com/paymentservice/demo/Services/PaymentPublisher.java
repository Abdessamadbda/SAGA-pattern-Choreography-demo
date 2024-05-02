package com.paymentservice.demo.Services;

import com.dtos.demo.events.PaymentEvent;
import com.paymentservice.demo.Entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class PaymentPublisher {
    @Autowired
    private Sinks.Many<PaymentEvent> paymentSinks;
    public void publishPaymentEvent(Payment newPayment, Long userId, double totalprice){
        // Create an order event with the necessary data and send it.
        PaymentEvent paymentEvent = new PaymentEvent(newPayment.getId(), userId, totalprice);
        paymentSinks.tryEmitNext(paymentEvent);
    }


}
