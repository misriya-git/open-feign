package com.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.service.model.Product;

@FeignClient(name = "product-service", url = "localhost:8081")
public interface ProductClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);
}
