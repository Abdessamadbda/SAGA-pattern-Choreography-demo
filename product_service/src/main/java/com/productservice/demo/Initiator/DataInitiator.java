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
    private ProductRepository productRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            productRepository.save(new Product("HTC Electric Shaver", 80, 0, "An electric shaver"));
            productRepository.save(new Product("Arduino Uno", 150, 20,"A microcontroller board"));
            productRepository.save(new Product("Canon EOS M100", 5500, 3,"Canon camera"));
        }
    }
}
