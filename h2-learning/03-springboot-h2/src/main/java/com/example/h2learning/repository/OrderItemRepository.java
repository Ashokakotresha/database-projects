package com.example.h2learning.repository;

import com.example.h2learning.entity.Order;
import com.example.h2learning.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}

