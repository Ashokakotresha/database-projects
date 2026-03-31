package com.example.h2learning.controller;

import com.example.h2learning.dto.OrderResponse;
import com.example.h2learning.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long id) {
        return userService.getOrdersByUserId(id);
    }
}

