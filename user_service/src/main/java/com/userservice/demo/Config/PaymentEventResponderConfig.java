package com.userservice.demo.Config;

import com.dtos.demo.events.PaymentEvent;
import com.dtos.demo.events.UserEvent;
import com.dtos.demo.events.UserBalanceState;
import com.userservice.demo.Entities.User;
import com.userservice.demo.Repositories.UserRepository;
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
    public Function<Flux<PaymentEvent>, Flux<UserEvent>> paymentEventProcessor() {
        return paymentEventFlux -> paymentEventFlux.flatMap(this::userBalanceCheck);
    }

    private Mono<UserEvent> userBalanceCheck(PaymentEvent paymentEvent){
        User user = userRepository.findById(paymentEvent.getUserId()).get();
        UserBalanceState balanceState = (user.getBalance() >= paymentEvent.getTotalprice())
                ? UserBalanceState.AVAILABLE : UserBalanceState.NOT_AVAILABLE;
        if(balanceState.equals(UserBalanceState.AVAILABLE)){
            user.setBalance(user.getBalance() - paymentEvent.getTotalprice());
            userRepository.save(user);
        }

        UserEvent userEvent = new UserEvent(
                paymentEvent.getPaymentId(),
                paymentEvent.getUserId(),
                paymentEvent.getTotalprice(),
                balanceState);
        return Mono.fromSupplier(() -> userEvent);
    }

}
