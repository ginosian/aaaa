package com.test.service.waiter.impl;


import com.test.entity.Order;
import com.test.entity.RestaurantTable;
import com.test.service.OrderCreationRequest;
import com.test.service.OrderUpdateRequest;
import com.test.service.waiter.WaiterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterServiceImpl implements WaiterService{


    @Override
    public List<RestaurantTable> getTables(String waiterId) {
        return null;
    }

    @Override
    public Order createOrder(OrderCreationRequest orderCreationRequest) {
        return null;
    }

    @Override
    public Order updateOrder(OrderUpdateRequest orderUpdateRequest) {
        return null;
    }
}
