package com.orderservice.demo.Services;

import com.dtos.demo.events.OrderEvent;
import com.orderservice.demo.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service

public class OrderPublisher {
    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;
    public void publishOrderEvent(Order newOrder,long userId ,long prodId, int qnt,long paymentId){
        // Create an order event with the necessary data and send it.
        OrderEvent orderEvent = new OrderEvent(newOrder.getId(), prodId,qnt,userId,paymentId);
        orderSinks.tryEmitNext(orderEvent);
    }

}
