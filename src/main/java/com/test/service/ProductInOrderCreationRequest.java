package com.test.service;

import lombok.Data;

@Data
public class ProductInOrderCreationRequest {

    private String productId;
    private String amount;
}
