package com.userservice.demo.Initiator;

import com.userservice.demo.Entities.User;
import com.userservice.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitiator implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            userRepository.save(new User( 1L,"amine.amine","AA",100.0));
            userRepository.save(new User( 2L,"mohamed.mohamed","VV",200.0));
            userRepository.save(new User( 3L,"ahmed.ahmed","aa",300.0));
        }
    }
}
