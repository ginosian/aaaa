package com.test.service.waiter;

import com.test.entity.Order;
import com.test.entity.RestaurantTable;
import com.test.service.OrderCreationRequest;
import com.test.service.OrderUpdateRequest;

import java.util.List;

public interface WaiterService {

    List<RestaurantTable> getTables(String waiterId);

    Order createOrder(OrderCreationRequest orderCreationRequest);

    Order updateOrder(OrderUpdateRequest orderUpdateRequest);
}
