package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.service.ProductService;
import com.chi.springecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home(Model model) {
        List<Product> product=productService.findAll();
        model.addAttribute("product", product);
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
}
