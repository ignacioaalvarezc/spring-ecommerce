package com.chi.springecommerce.repository;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser (User user);
}
