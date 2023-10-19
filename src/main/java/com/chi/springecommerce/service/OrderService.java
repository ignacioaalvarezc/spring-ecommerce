package com.chi.springecommerce.service;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Order save (Order order);
    String generateOrderNumber();
    List<Order> findByUser (User user);
    Optional<Order> findById(Integer id);
}
