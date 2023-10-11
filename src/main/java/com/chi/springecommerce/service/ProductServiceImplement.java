package com.chi.springecommerce.service;

// IMPORTS OF PROGRAM
import com.chi.springecommerce.model.Product;
import com.chi.springecommerce.repository.ProductRepository;

// IMPORTS OF SPRINGFRAMEWORK
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// IMPORTS OF JAVA.UTIL
import java.util.Optional;
import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // SAVE METHOD
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // LIST METHOD
    @Override
    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // UPDATE METHOD
    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    // DELETE METHOD
    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
