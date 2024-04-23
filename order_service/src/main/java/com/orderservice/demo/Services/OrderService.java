package com.orderservice.demo.Services;

public class OrderService {

    public Order saveOrderInDB(long prodId, int qnt) {
        // Step 1: Get the product price from the product_service using OpenFeign
        Product product = productService.getProductById(prodId); // Assuming productService is an instance of Feign client interface

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
        Order order = orderRepository.findByOrderId(orderId).orElse(null); // Assuming orderRepository is your JPA repository

        if (order == null) {
            // Handle the case when order is not found
            return; // Return without updating if order is not found
        }

        // Step 2: Depending on the received product event, change the order state
        if (prdct.getType() == ProductEventType.PROCESSING) {
            order.setStatus(OrderStatus.PROCESSING);
        } else if (prdct.getType() == ProductEventType.FAILED) {
            order.setStatus(OrderStatus.FAILED);
        }

        // Step 3: Update the DB order data
        orderRepository.save(order);
    }
}
