package com.example.h2learning.service;

import com.example.h2learning.dto.*;
import com.example.h2learning.entity.*;
import com.example.h2learning.exception.ResourceNotFoundException;
import com.example.h2learning.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemRequest.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPriceAtPurchase(product.getPrice());

            orderItemRepository.save(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            itemResponses.add(new OrderItemResponse(
                    product.getId(),
                    product.getName(),
                    item.getQuantity(),
                    item.getPriceAtPurchase()
            ));
        }

        savedOrder.setTotalAmount(total);
        orderRepository.save(savedOrder);

        return new OrderResponse(
                savedOrder.getId(),
                user.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getCreatedAt(),
                itemResponses
        );
    }

    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        List<OrderItemResponse> itemResponses = orderItemRepository.findByOrder(order).stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                itemResponses
        );
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        return orderRepository.findByUser(user).stream()
                .map(order -> {
                    List<OrderItemResponse> items = orderItemRepository.findByOrder(order).stream()
                            .map(item -> new OrderItemResponse(
                                    item.getProduct().getId(),
                                    item.getProduct().getName(),
                                    item.getQuantity(),
                                    item.getPriceAtPurchase()
                            ))
                            .toList();

                    return new OrderResponse(
                            order.getId(),
                            user.getId(),
                            order.getTotalAmount(),
                            order.getCreatedAt(),
                            items
                    );
                })
                .toList();
    }
}

