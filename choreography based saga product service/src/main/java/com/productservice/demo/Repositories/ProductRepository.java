package com.productservice.demo.Repositories;

import com.productservice.demo.Entities.Product;

public class ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product product);
}
