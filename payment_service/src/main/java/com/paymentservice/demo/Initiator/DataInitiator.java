package com.productservice.demo.Initiator;

import com.productservice.demo.Entities.Product;
import com.productservice.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitiator implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            paymentRepository.save(new Payment( 80, 10,100, "Cash"));
            paymentRepository.save(new Payment( 150, 20,120,"Par carte bancaire"));
            paymentRepository.save(new Payment( 5500, 3,130,"Par carte bancaire"));
        }
    }
}
