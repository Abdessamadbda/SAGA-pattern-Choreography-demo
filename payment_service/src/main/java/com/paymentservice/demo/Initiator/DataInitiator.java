package com.paymentservice.demo.Initiator;

import com.dtos.demo.events.PaymentState;
import com.paymentservice.demo.Entities.Payment;
import com.paymentservice.demo.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitiator implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            paymentRepository.save(new Payment(100.0 , PaymentState.SUCCESSFULL));
            paymentRepository.save(new Payment(120.0 , PaymentState.SUCCESSFULL));
            paymentRepository.save(new Payment(130.0, PaymentState.SUCCESSFULL));
        }
    }
}
