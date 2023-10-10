package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.model.User;
import com.chi.springecommerce.service.ProductService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String show() {
        return "product/show";
    }
    @GetMapping("/create")
    public String create() {
        return "product/create";
    }

    @PostMapping("/save")
    public String saveProduct(Product product) {
        LOGGER.info("Este es el objeto producto {}",product);
        User user = new User(1, "", "", "", "", "", "", "", "");
        product.setUser(user);
        productService.save(product);
        return "redirect:/product";
    }
}
