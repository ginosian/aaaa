package com.test.service;


import com.test.misc.OrderStatus;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderUpdateRequest {

    private String orderId;
    private OrderStatus orderStatus;
    private Set<ProductInOrderUpdateRequest> productInOrderUpdateRequests = new HashSet<>();

}
