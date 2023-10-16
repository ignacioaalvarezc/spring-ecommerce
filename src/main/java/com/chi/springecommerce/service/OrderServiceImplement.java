package com.chi.springecommerce.service;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImplement implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImplement(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public String generateOrderNumber(){
        int number = 0;
        String concatenatedNumber = "";
        List<Order> orders = findAll();
        List<Integer> numbers = new ArrayList<Integer>();
        orders.stream().forEach(o -> numbers.add(Integer.parseInt(o.getNumber())));
        if (orders.isEmpty()) {
            number = 1;
        } else {
            number = numbers.stream().max(Integer::compare).get();
            number++;
        }
        if (number<10) {
            concatenatedNumber = "000000000" + String.valueOf(number);
        } else if (number<100) {
            concatenatedNumber = "00000000" + String.valueOf(number);
        } else if (number<1000) {
            concatenatedNumber = "0000000" + String.valueOf(number);
        } else if (number<10000) {
            concatenatedNumber = "000000" + String.valueOf(number);
        } else if (number<100000) {
            concatenatedNumber = "00000" + String.valueOf(number);
        } else if (number<1000000) {
            concatenatedNumber = "0000" + String.valueOf(number);
        } else if (number<10000000) {
            concatenatedNumber = "000" + String.valueOf(number);
        } else if (number<100000000) {
            concatenatedNumber = "00" + String.valueOf(number);
        } else if (number<1000000000) {
            concatenatedNumber = "0" + String.valueOf(number);
        }
        return concatenatedNumber;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
