package com.orderservice.demo.Services;

public class OrderService {
    public Order saveOrderInDB(long prodId, int qnt){
        // (To do) Get the concerned product from the product_service using OpenFeign (In order to get the product price)
        // (To do) Create and save the new order in the database.
        // Publish the orderEvent to the product service
        orderPublisher.publishOrderEvent(newOrder, prodId, qnt);
        return newOrder;
    }

    public void updateOrde(ProductEvent prdct){
        // (To do) Get the concerned order from the database using the ProductEvent’s orderId
        // (To do) Depending on the received product event, change the order state to PROCESSING or FAILED
        // (To do) Update the DB order data.
    }
}
}
