package com.example.h2learning.service;

import com.example.h2learning.dto.CreateProductRequest;
import com.example.h2learning.dto.ProductResponse;
import com.example.h2learning.entity.Product;
import com.example.h2learning.exception.ResourceNotFoundException;
import com.example.h2learning.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Product saved = productRepository.save(product);
        return new ProductResponse(saved.getId(), saved.getName(), saved.getPrice());
    }
}
