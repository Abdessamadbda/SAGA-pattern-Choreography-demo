package com.paymentservice.demo.Proxies;
import com.orderservice.demo.Entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "choreography-SAGA-payment-service")
public interface Paymentproxy {

    @GetMapping("/{paymentId}")
    public Payment getPaymentByid(@PathVariable long paymentId);

}
