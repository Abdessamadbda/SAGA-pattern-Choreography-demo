package com.orderservice.demo.Initiator;

import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

public class DataInitiator {
    @Component
    public class DataInitiator implements
            ApplicationListener<ContextRefreshedEvent> {
        @Autowired
        private OrderRepository orderRepository;
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if(event.getApplicationContext().getParent() == null) {
                orderRepository.save(new Order(160, new Date(), OrderStatus.CREATED));
                orderRepository.save(new Order(360, new Date(), OrderStatus.CREATED));
                orderRepository.save(new Order(5500, new Date(), OrderStatus.CREATED));
            }
        }
    }
}
