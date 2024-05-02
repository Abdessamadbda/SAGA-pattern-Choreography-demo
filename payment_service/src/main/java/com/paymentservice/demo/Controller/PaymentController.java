package com.paymentservice.demo.Controller;

import com.paymentservice.demo.Entities.Payment;
import com.paymentservice.demo.Repositories.PaymentRepository;
import com.paymentservice.demo.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    /*
     * Order service implementation
     * */
    @Autowired
    private PaymentService paymentService;

    /*
     * Get the order list from the database
     * */
    


    /*
     * Get information about a specific product
     * */
    @GetMapping("/{paymentId}")
    public Payment getPaymentByid(@PathVariable long paymentId){
        return paymentRepository.findById(paymentId).get();
    }

    @GetMapping("/new/{userId}/{orderId}")
    public Payment createPayment(@PathVariable long userId, @PathVariable long orderId){
        // saveOrder of the order service
        return paymentService.savePaymentInDB(userId,orderId);

    }


    /*
     * Create new order with CREATED state.
     * Ceck the product stock availability.
     * If the product is available, change the order state to PROCESSING.
     * If the product is out of stock we will delete the order.
     * */
    

}
