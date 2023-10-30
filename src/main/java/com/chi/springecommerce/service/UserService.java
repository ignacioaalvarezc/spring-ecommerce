package com.chi.springecommerce.service;

import com.chi.springecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
    User save (User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
