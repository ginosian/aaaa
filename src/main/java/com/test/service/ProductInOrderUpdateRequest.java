package com.test.service;


import lombok.Data;

@Data
public class ProductInOrderUpdateRequest {

    private String productInOrderId;
    private String productId;
    private double amount;
}
