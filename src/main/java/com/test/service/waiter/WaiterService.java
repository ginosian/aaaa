package com.test.service.waiter;

import com.test.entity.RestaurantTable;

import java.util.List;

public interface WaiterService {
    
    List<RestaurantTable> getTables(String waiterId);
    
    void createOrder(OrderCreationRequest orderCreationRequest);
    
    void updateOrder(OrderUpdateRequest orderUpdateRequest);
}
