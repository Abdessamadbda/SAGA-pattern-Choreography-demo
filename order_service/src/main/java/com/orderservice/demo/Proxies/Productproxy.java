package com.orderservice.demo.Proxies;
import com.orderservice.demo.Entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClientFactory(name = "choreography-SAGA-product-service")
public interface Productproxy {

    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable long prodId);

}
