package com.test.service;

import lombok.Data;

import java.util.Set;

@Data
public class OrderCreationRequest {

    private String tableId;
    private Set<ProductInOrderCreationRequest> productInOrderCreationRequests;
}
