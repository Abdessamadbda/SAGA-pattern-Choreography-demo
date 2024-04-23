package com.productservice.demo.Config;

import com.productservice.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OrderEventResponderConfig {
    @Autowired
    private ProductRepository productRepository;

    @Bean
    public Function<Flux<OrderEvent>, Flux<ProductEvent>> orderEventProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::productStockCheck);
    }

    private Mono<ProductEvent> productStockCheck(OrderEvent orderEvent) {
        // (To do) Get the product from the DB using the orderEvent prodId.
        return productRepository.findById(orderEvent.getProdId())
                .flatMap(product -> {
                    // (To do) Create a ProductStockState variable with an AVAILABLE value if the product item number
                    // is higher than the orderEvent qnt, else an OUT_OF_STOCK should be assigned.
                    ProductStockState stockAvailability = product.getQuantity() >= orderEvent.getProdqnt() ?
                            ProductStockState.AVAILABLE : ProductStockState.OUT_OF_STOCK;

                    // (To do) Subtract the number of items requested from the product available item number.
                    // Then update the product values in the database.
                    if (stockAvailability == ProductStockState.AVAILABLE) {
                        product.setQuantity(product.getQuantity() - orderEvent.getProdqnt());
                        return productRepository.save(product)
                                .thenReturn(new ProductEvent(
                                        orderEvent.getOrderId(),
                                        orderEvent.getProdId(),
                                        orderEvent.getProdqnt(),
                                        stockAvailability
                                ));
                    } else {
                        // If out of stock, just return a ProductEvent indicating unavailability
                        return Mono.just(new ProductEvent(
                                orderEvent.getOrderId(),
                                orderEvent.getProdId(),
                                orderEvent.getProdqnt(),
                                stockAvailability
                        ));
                    }
                });
    }

}
