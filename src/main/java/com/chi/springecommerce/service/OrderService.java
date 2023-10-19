package com.chi.springecommerce.service;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order save (Order order);
    String generateOrderNumber();
    List<Order> findByUser (User user);
}
