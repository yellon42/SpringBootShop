package com.testProject.Shop.db.service.api;

import com.testProject.Shop.db.service.api.request.BuyProductRequest;
import com.testProject.Shop.db.service.api.response.BuyProductResponse;

public interface ShoppingService {
    BuyProductResponse buyProduct(BuyProductRequest request);
}
