package com.cosmocats.service.implementation;

import com.cosmocats.domain.Order;
import com.cosmocats.service.OrderService;
import com.cosmocats.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderImplementation implements OrderService {

    private final ProductService productService;

    private final List<Order> orderList = new ArrayList<>();

    public OrderImplementation(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public Optional<Order> getOrderById(UUID orderId) {
        return orderList.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst();
    }
}
