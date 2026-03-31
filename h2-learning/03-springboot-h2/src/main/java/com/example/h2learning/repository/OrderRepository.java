package com.example.h2learning.repository;

import com.example.h2learning.entity.Order;
import com.example.h2learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

