package com.orderservice.demo.Services;

import com.dtos.demo.events.OrderEvent;
import com.orderservice.demo.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class OrderPublisher {
    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;
    public void publishOrderEvent(Order newOrder, long prodId, int qnt){
        // Create an order event with the necessary data and send it.
        OrderEvent orderEvent = new OrderEvent(newOrder.getOrderId(), prodId, qnt);
        orderSinks.tryEmitNext(orderEvent);
    }

}
