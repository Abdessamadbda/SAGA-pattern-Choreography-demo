package com.productservice.demo.Controller;

import com.productservice.demo.Entities.Product;
import com.productservice.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /*
     * Get the product list from the database
     * */
    @GetMapping("/AllProducts")
    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    /*
     * Get information about a specific product
     * */
    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable long prodId){
        return productRepository.findById(prodId).get();
    }

    /*
     * Check if the amount of a particular product is enough to fill an order
     * */
    @GetMapping("/Stock/{prodId}/{qnt}")
    public boolean stockCheck(@PathVariable long prodId, @PathVariable int qnt){
        return  productRepository.findById(prodId).get().getStock() >= qnt;
    }

}