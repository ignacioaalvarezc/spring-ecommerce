package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.OrderDetail;
import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.model.User;
import com.chi.springecommerce.service.OrderDetailService;
import com.chi.springecommerce.service.OrderService;
import com.chi.springecommerce.service.ProductService;
import com.chi.springecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    List<OrderDetail> details = new ArrayList<OrderDetail>();
    Order order = new Order();

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("product", productService.findAll());
        return "user/home";
    }

    @GetMapping("shop/{id}")
    public String shop(@PathVariable Integer id,
                       Model model) {
        log.info("Id producto enviado como paramtero {}", id);
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        model.addAttribute("product", product);
        return "user/shop";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,
                          @RequestParam Integer amount,
                          Model model) {
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        double totalAmount = 0;
        Optional<Product> optionalProduct = productService.get(id);
        log.info("Producto añadido: {}", optionalProduct.get());
        log.info("Cantidad: {}", amount);
        product = optionalProduct.get();
        orderDetail.setAmount(amount);
        orderDetail.setPrice(product.getPrice());
        orderDetail.setProduct_name(product.getName());
        orderDetail.setTotal(product.getPrice()*amount);
        orderDetail.setProduct(product);

        // VALIDACION PARA QUE EL PRODUCTO NO SE AÑADA 2 VECES
        Integer idProduct = product.getId();
        boolean joined = details.stream().anyMatch(p -> p.getProduct().getId()==idProduct);
        if (!joined) {
            details.add(orderDetail);
        }

        totalAmount = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal((totalAmount));
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "user/cart";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id,
                                    Model model) {
        List<OrderDetail> newOrders = new ArrayList<OrderDetail>();
        for(OrderDetail orderDetail: details) {
            if(orderDetail.getProduct().getId()!=id) {
                newOrders.add(orderDetail);
            }
        }
        details = newOrders;
        double totalAmount = 0;
        totalAmount = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal((totalAmount));
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "user/cart";
    }

    @GetMapping("/getCart")
    public String getCart(Model model) {
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "/user/cart";
    }

    @GetMapping("/order")
    public String order(Model model) {
        User user = userService.findById(1).get();
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "user/resume_order";
    }

    // METHOD THAT SAVES THE ORDER
    @GetMapping("/saveOrder")
    public String saveOrder() {
        Date creationDate = new Date();
        order.setCreation_date(creationDate);
        order.setNumber(orderService.generateOrderNumber());
        // USER
        User user = userService.findById(1).get();
        order.setUser(user);
        orderService.save(order);
        // DETAILS
        for (OrderDetail dt:details) {
            dt.setOrder(order);
            orderDetailService.save(dt);
        }
        // CLEAN VALUES
        order = new Order();
        details.clear();
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String name,
                                Model model) {
        log.info("Nombre del producto: {}", name);
        List<Product> product = productService.findAll().stream()
                .filter(p -> p.getName().contains(name))
                .collect(Collectors.toList());
        model.addAttribute("product", product);
        return "user/home";
    }
}
