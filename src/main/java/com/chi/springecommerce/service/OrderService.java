package com.chi.springecommerce.service;

import com.chi.springecommerce.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order save (Order order);
    String generateOrderNumber();
}
