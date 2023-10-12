package com.chi.springecommerce.service;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplement implements OrderService {
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
