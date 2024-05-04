package com.orderservice.demo.Services;

import com.dtos.demo.events.OrderState;
import com.dtos.demo.events.ProductStockState;
import com.dtos.demo.events.PaymentState;

import com.dtos.demo.events.ProductEvent;

import com.dtos.demo.events.PaymentEvent;
import com.dtos.demo.events.AnotherPaymentEvent;

import com.dtos.demo.events.UserBalanceState;
import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Entities.Payment;
import com.orderservice.demo.Entities.Product;

import com.orderservice.demo.Proxies.Productproxy;
import com.orderservice.demo.Proxies.Paymentproxy;
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
    private Paymentproxy paymentproxy;

    @Autowired
    private Userproxy userproxy;

    

    //Order repository injection
    @Autowired
    private OrderRepository orderRepository;

    //Inject the order publisher, which will publish the order event
    @Autowired
    private OrderPublisher orderPublisher;
    @Autowired
    private AnotherOrderPublisher anotherOrderPublisher;
    
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
        anotherOrderPublisher.publishOrderEvent(newOrder,user.getId(), prodId, qnt,paymentId);
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
    public void updateOrderPayment(AnotherPaymentEvent payment){
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(payment.getOrderId());
        System.out.println("++++++++++++++++++++++++++++++++");
        long orderId = payment.getOrderId();
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(payment.getPaymentId());
        System.out.println("++++++++++++++++++++++++++++++++");
        Payment payment1=paymentproxy.getPaymentByid(payment.getOrderId());
        Optional<Order> newOrder = orderRepository.findById(orderId);
        if(newOrder.isPresent()){
         
            OrderState newOrderState = payment1.getPaymentState().equals(PaymentState.SUCCESSFULL)  ?
                    OrderState.CREATED : OrderState.FAILED;
            System.out.println(newOrderState);
            newOrder.get().setOrderState(newOrderState);
            orderRepository.save(newOrder.get());

        }
    }


}