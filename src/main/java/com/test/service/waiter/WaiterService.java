package com.test.service.waiter;

import com.test.entity.RestaurantTable;
import com.test.service.OrderCreationRequest;
import com.test.service.OrderUpdateRequest;

import java.util.List;

public interface WaiterService {

    List<RestaurantTable> getTables(String waiterId);

    void createOrder(OrderCreationRequest orderCreationRequest);

    void updateOrder(OrderUpdateRequest orderUpdateRequest);
}
