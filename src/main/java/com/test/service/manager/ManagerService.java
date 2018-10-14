package com.test.service.manager;

import com.test.entity.Product;
import com.test.entity.RestaurantTable;
import com.test.service.ProductCreationRequest;
import com.test.service.TableCreationRequest;

public interface ManagerService {

    RestaurantTable createTable(TableCreationRequest tableCreationRequest);
    Product createProduct(ProductCreationRequest productCreationRequest);
    RestaurantTable assignTableToWaiter(String tableId, String waiterId);
}
