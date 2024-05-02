package com.orderservice.demo.Services;

import com.dtos.demo.events.OrderState;
import com.dtos.demo.events.ProductStockState;
import com.dtos.demo.events.PaymentState;

import com.dtos.demo.events.ProductEvent;

import com.dtos.demo.events.PaymentEvent;

import com.dtos.demo.events.UserBalanceState;
import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Entities.Product;
import com.orderservice.demo.Proxies.Productproxy;
import com.orderservice.demo.Proxies.Userproxy;
import com.orderservice.demo.Repositories.OrderRepository;
import com.orderservice.demo.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class OrderService {

    //Product proxy injection
    @Autowired
    private Productproxy productproxy;

    @Autowired
    private Userproxy userproxy;

    

    //Order repository injection
    @Autowired
    private OrderRepository orderRepository;

    //Inject the order publisher, which will publish the order event
    @Autowired
    private OrderPublisher orderPublisher;
    
    public Order saveOrderInDB(long userId,long paymentId, long prodId, int qnt){

        User user = userproxy.getUserByid(userId);
        //Get the concerned product from the product service using OpenFeign (In order to get the product price)
        Product prod = productproxy.getProductByid(prodId);
        Order newOrder = new Order(
                prod.getPrice()*qnt,
                new Date(),
                OrderState.PROCESSING);
    
        

        // Save order in the database
        orderRepository.save(newOrder);
        // Publish the orderEvent to the product service
        orderPublisher.publishOrderEvent(newOrder, prodId,user.getId(), qnt,paymentId);
        return newOrder;
    }

    public void updateOrder(ProductEvent prdct){

        Optional<Order> newOrder = orderRepository.findById(prdct.getOrderId());
        if(newOrder.isPresent()){
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.println(prdct.getStockAvailability());
            System.out.println("++++++++++++++++++++++++++++++++");
            OrderState newOrderState = prdct.getStockAvailability().equals(ProductStockState.AVAILABLE)  ?
                    OrderState.PAYMENT_PROCESSING : OrderState.FAILED;

            newOrder.get().setOrderState(newOrderState);
            orderRepository.save(newOrder.get());

        }
    }
    public void updateOrderPayment(PaymentEvent payment){

        Optional<Order> optionalOrder = orderRepository.findById(payment.getOrderId());
    
    if (optionalOrder.isPresent()) {
        Order order = optionalOrder.get();

        if (payment.getPaymentState().equals(PaymentState.SUCCESSFULL)) {
            order.setOrderState(OrderState.CREATED);
        } else {
            order.setOrderState(OrderState.FAILED);
        }

        orderRepository.save(order);
    }
    }


}