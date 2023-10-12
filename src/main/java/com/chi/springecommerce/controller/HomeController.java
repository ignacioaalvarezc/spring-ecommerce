package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Order;
import com.chi.springecommerce.model.OrderDetail;
import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

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
        orderDetail.setName(product.getName());
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
}
