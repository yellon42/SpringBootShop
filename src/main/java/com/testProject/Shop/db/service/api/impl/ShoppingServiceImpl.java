package com.testProject.Shop.db.service.api.impl;

import com.testProject.Shop.db.service.api.*;
import com.testProject.Shop.db.service.api.request.BuyProductRequest;
import com.testProject.Shop.db.service.api.response.BuyProductResponse;
import com.testProject.Shop.domain.BoughtProduct;
import com.testProject.Shop.domain.Customer;
import com.testProject.Shop.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;
    private final BoughtProductService boughtProductService;

    public ShoppingServiceImpl(ProductService productService, CustomerService customerService, CustomerAccountService customerAccountService, BoughtProductService boughtProductService) {
        this.productService = productService;
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
        this.boughtProductService = boughtProductService;
    }

    @Override
    public BuyProductResponse buyProduct(BuyProductRequest request) {
        int productId = request.getProductId();
        int customerId = request.getCustomerId();

        Product product = productService.get(productId);
        if (product == null) {
            return new BuyProductResponse(false, "Product with id: " + productId + " doesn't exist");
        }

        Customer customer = customerService.get(customerId);
        if (customer == null) {
            return new BuyProductResponse(false, "Customer with id: " + customerId + " doesn't exist");
        }

        if (product.getAvailable() < request.getQuantity()) {
            return new BuyProductResponse(false, "Not enough products in stock");
        }

        Double customerMoney = customerAccountService.getmoney(customerId);
        if (customerMoney == null) {
            return new BuyProductResponse(false, "Customer with id: " + customerId + " doesn't have account");
        } else {
            double totalPriceOfRequest = product.getPrice() * request.getQuantity();
            if (customerMoney >= totalPriceOfRequest) {
                productService.updateAvailableInternal(productId, product.getAvailable() - request.getQuantity());
                customerAccountService.setMoney(customerId, customerMoney - totalPriceOfRequest);
                boughtProductService.add(new BoughtProduct(productId, customerId, request.getQuantity()));
                return new BuyProductResponse(true);
            } else {
                return new BuyProductResponse(false, "Customer with id: " + customerId + " doesn't have enough money");
            }
        }
    }
}
