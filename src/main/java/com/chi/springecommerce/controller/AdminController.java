package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.service.OrderService;
import com.chi.springecommerce.service.ProductService;
import com.chi.springecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private Logger logg = LoggerFactory.getLogger(AdminController.class);

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

    @GetMapping("/sales")
    public String sales(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin/sales";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id,
                         Model model) {
        logg.info("id de la orden{}", id);
        Order order = orderService.findById(id).get();
        model.addAttribute("details", order.getOrderDetail());
        return "admin/sale_detail";
    }
}
