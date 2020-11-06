package com.testProject.Shop.db.service.api;

import com.testProject.Shop.db.service.api.request.UpdateProductRequest;
import com.testProject.Shop.domain.Product;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    @Nullable
    Product get(int id);

    @Nullable
    Integer add(Product product);   //return generated id

    void delete(int id);

    void update(int id, UpdateProductRequest request);

    void updateAvailableInternal(int id, int available);
}
