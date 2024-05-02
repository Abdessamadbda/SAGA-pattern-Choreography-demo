package com.paymentservice.demo.Proxies;
import com.paymentservice.demo.Entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "choreography-SAGA-user-service")
public interface Userproxy {

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId);

}
