package com.paymentservice.demo.Services;

import com.dtos.demo.events.PaymentState;
import com.dtos.demo.events.UserBalanceState;

import com.dtos.demo.events.UserEvent;

import com.paymentservice.demo.Entities.Order;
import com.paymentservice.demo.Entities.User;

import com.paymentservice.demo.Entities.Payment;
import com.paymentservice.demo.Proxies.Userproxy;
import com.paymentservice.demo.Proxies.Orderproxy;

import com.paymentservice.demo.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PaymentService {

    //Product proxy injection
    @Autowired
    private Userproxy userproxy;
    @Autowired
    private Orderproxy orderproxy;
    //Order repository injection
    @Autowired
    private PaymentRepository paymentRepository;

    //Inject the order publisher, which will publish the order event
    @Autowired
    private PaymentPublisher paymentPublisher;

    public Payment savePaymentInDB(Long userId, Long orderId){

        //Get the concerned product from the product service using OpenFeign (In order to get the product price)
        Order order=orderproxy.getOrderById(orderId);
        User user=userproxy.getUserById(userId);

        Payment newPayment = new Payment(
                
                order.getPrice(),
                PaymentState.PROCESSING);

        // Save order in the database
        paymentRepository.save(newPayment);
        // Publish the orderEvent to the product service
        paymentPublisher.publishPaymentEvent(newPayment, userId, order.getPrice());
        return newPayment;
    }

    public void updatePayment(UserEvent user){

        Optional<Payment> newPayment = paymentRepository.findById(user.getPaymentId());
        if(newPayment.isPresent()){
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++");
            PaymentState newPaymentState = user.getBalanceAvailability().equals(UserBalanceState.AVAILABLE) ?
                    PaymentState.SUCCESSFULL : PaymentState.FAILED;

            newPayment.get().setPaymentState(newPaymentState);
            paymentRepository.save(newPayment.get());

        }
    }


}