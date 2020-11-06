package com.testProject.Shop.db.service.api.impl;

import com.testProject.Shop.db.repository.ProductRepository;
import com.testProject.Shop.db.service.api.request.UpdateProductRequest;
import com.testProject.Shop.db.service.api.ProductService;
import com.testProject.Shop.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getAll();
    }

    @Override
    public Product get(int id) {
        return productRepository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public void update(int id, UpdateProductRequest request) {
        productRepository.update(id, request);
    }

    @Override
    public void updateAvailableInternal(int id, int newAvailable) {
        productRepository.updateAvailable(id, newAvailable);
    }
}
