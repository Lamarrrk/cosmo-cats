package com.cosmocats.service;

import com.cosmocats.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID orderId);
}

