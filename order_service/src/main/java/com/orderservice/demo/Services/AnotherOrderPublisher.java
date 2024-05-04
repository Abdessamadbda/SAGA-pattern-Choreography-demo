package com.orderservice.demo.Services;


import com.orderservice.demo.Entities.Order;
import com.dtos.demo.events.AnotherOrderEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;


@Service

public class AnotherOrderPublisher {
    @Autowired
    private Sinks.Many<AnotherOrderEvent> anotherorderSinks;
    public void publishOrderEvent(Order newOrder,long userId ,long prodId, int qnt,long paymentId){
        // Create an order event with the necessary data and send it.
        AnotherOrderEvent orderEvent = new AnotherOrderEvent(newOrder.getId(), prodId,qnt,userId,paymentId);
        anotherorderSinks.tryEmitNext(orderEvent);
    }

}
