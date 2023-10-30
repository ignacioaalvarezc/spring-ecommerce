package com.chi.springecommerce.controller;

import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.model.User;
import com.chi.springecommerce.service.ProductService;
import com.chi.springecommerce.service.UploadFileService;
import com.chi.springecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired

    private ProductService productService;

    @Autowired
    private UploadFileService upload;

    @Autowired
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, UploadFileService upload) {
        this.productService = productService;
        this.upload = upload;
    }

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("product", productService.findAll());
        return "product/show";
    }
    @GetMapping("/create")
    public String create() {
        return "product/create";
    }

    @PostMapping("/save")
    public String save(Product product,
                       @RequestParam("image") MultipartFile file,
                       Model model,
                       HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}",product);
        User user = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
        product.setUser(user);
        //  IMAGEN
        if (product.getId()==null) {
            String nameImage = upload.saveImage(file);
            product.setImage(nameImage);
        } else {

        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProduct=productService.get(id);
        product = optionalProduct.get();
        LOGGER.info("Producto buscado: {}", product);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("image") MultipartFile file) throws IOException {
        Product p = new Product();
        p = productService.get(product.getId()).get();
        if (file.isEmpty()) {
            product.setImage(p.getImage());
        } else {
            if (!p.getImage().equals("default.jpg")) {
                upload.deleteImage(p.getImage());
            }
            String nameImage = upload.saveImage(file);
            product.setImage(nameImage);
        }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Product p = new Product();
        p = productService.get(id).get();
        if (p.getImage(). equals("default.jpg")) {
            upload.deleteImage(p.getImage());
        }
        productService.delete(id);
        return "redirect:/product";
    }


}
