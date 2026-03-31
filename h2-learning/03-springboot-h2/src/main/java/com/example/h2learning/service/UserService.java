package com.example.h2learning.service;

import com.example.h2learning.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final OrderService orderService;

    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}

