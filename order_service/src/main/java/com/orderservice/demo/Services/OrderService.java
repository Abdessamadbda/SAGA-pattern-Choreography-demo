package com.orderservice.demo.Services;

import com.dtos.demo.events.OrderState;
import com.dtos.demo.events.ProductStockState;
import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Entities.Product;
import com.orderservice.demo.Proxies.Productproxy;
import com.orderservice.demo.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.dtos.demo.events.ProductEvent;


@Service
public class OrderService {
    @Autowired
    private Productproxy productproxy;
    @Autowired
    private OrderPublisher orderPublisher;
    @Autowired
    private OrderRepository orderRepository;
    public Order saveOrderInDB(long prodId, int qnt) {
        // Step 1: Get the product price from the product_service using OpenFeign
        Product product = productproxy.getProductByid(prodId);
        // Assuming productService is an instance of Feign client interface

        // Calculate total price
        double totalPrice = product.getPrice() * qnt;

        // Step 2: Create and save the new order in the database
        Order newOrder = new Order();
        newOrder.setProductId(prodId);
        newOrder.setQuantity(qnt);
        newOrder.setTotalPrice(totalPrice);
        // Save newOrder to the database using your ORM or JDBC

        // Step 3: Publish the order event to the product service
        orderPublisher.publishOrderEvent(newOrder, prodId, qnt);

        return newOrder;
    }

    public void updateOrder(ProductEvent prdct) {
        long orderId = prdct.getOrderId();
        Order order = orderRepository.findById(orderId).orElse(null); // Assuming orderRepository is your JPA repository

        if (order == null) {
            // Handle the case when order is not found
            return; // Return without updating if order is not found
        }

        // Step 2: Depending on the received product event, change the order state
        if (prdct.getStockAvailability() == ProductStockState.AVAILABLE) {
            order.setOrderStatus(OrderState.CREATED);
        } else if (prdct.getStockAvailability() == ProductStockState.OUT_OF_STOCK) {
            order.setOrderStatus(OrderState.FAILED);
        }

        // Step 3: Update the DB order data
        orderRepository.save(order);
    }
}
