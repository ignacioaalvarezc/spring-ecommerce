package com.chi.springecommerce.service;

import com.chi.springecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
    User save (User user);
}
