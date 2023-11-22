package com.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.exception.ProductNotFoundException;
import com.order.service.feign.ProductClient;
import com.order.service.model.Order;
import com.order.service.model.Product;
import com.order.service.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Product product = productClient.getProductById(order.getProductId());
        if (product != null) {
            return orderRepository.save(order);
        } else {
            throw new ProductNotFoundException("Product Not Found!");
        }
    }
}