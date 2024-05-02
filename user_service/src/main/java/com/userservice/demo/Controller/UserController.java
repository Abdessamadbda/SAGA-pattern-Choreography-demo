package com.userservice.demo.Controller;

import com.userservice.demo.Entities.User;
import com.userservice.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*
     * Get the product list from the database
     * */
    @GetMapping("/AllUsers")
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    /*
     * Get information about a specific product
     * */
    @GetMapping("/{userId}")
    public User getUserByid(@PathVariable long userId){
        return userRepository.findById(userId).get();
    }

    /*
     * Check if the amount of a particular product is enough to fill an order
     * */
   

}