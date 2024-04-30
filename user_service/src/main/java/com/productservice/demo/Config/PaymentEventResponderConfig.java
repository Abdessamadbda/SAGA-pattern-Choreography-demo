package com.productservice.demo.Config;

import com.dtos.demo.events.OrderEvent;
import com.dtos.demo.events.ProductEvent;
import com.dtos.demo.events.UserBalanceState;
import com.productservice.demo.Entities.Product;
import com.productservice.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration

public class PaymentEventResponderConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public Function<Flux<OrderEvent>, Flux<UserEvent>> paymentEventProcessor() {
        return paymentEventFlux -> paymentEventFlux.flatMap(this::userBalanceCheck);
    }

    private Mono<ProductEvent> userBalanceCheck(OrderEvent orderEvent){
        User user = userRepository.findById(paymentEvent.getUserId()).get();
        BalaanceState balanceState = (user.getBalance() >= paymentEvent.getTotalprice())
                ? UserBalanceState.AVAILABLE : ProductStockState.NOT_AVAILABLE;
        if(balanceState.equals(UserBalanceState.AVAILABLE)){
            user.setBalance(user.getBalance() - paymentEvent.getTotalprice());
            productRepository.save(product);
        }

        UserEvent userEvent = new UserEvent(
                paymentEvent.getPaymentId(),
                paymentEvent.getUserId(),
                paymentEvent.getTotalprice(),
                balanceState);
        return Mono.fromSupplier(() -> userEvent);
    }

}
