package com.test.service.manager.impl;


import com.test.entity.ApiUser;
import com.test.entity.Product;
import com.test.entity.RestaurantTable;
import com.test.repository.ApiUserRepository;
import com.test.repository.ProductRepository;
import com.test.repository.RestaurantTableRepository;
import com.test.service.ProductCreationRequest;
import com.test.service.TableCreationRequest;
import com.test.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApiUserRepository apiUserRepository;


    @Override
    public RestaurantTable createTable(TableCreationRequest tableCreationRequest) {
        notNull(tableCreationRequest, "tableCreationRequest can not be null");
        final String tableName = tableCreationRequest.getName();
        notEmpty(tableName, "tableCreationRequest.tableName can not be null or empty");
        final RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setName(tableName);
        return restaurantTableRepository.save(restaurantTable);
    }

    @Override
    public Product createProduct(ProductCreationRequest productCreationRequest) {
        notNull(productCreationRequest, "productCreationRequest can not be null");
        final String productName = productCreationRequest.getName();
        notEmpty(productName, "productCreationRequest.productName can not be null or empty");
        final Product product = new Product();
        product.setName(productName);
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public RestaurantTable assignTableToWaiter(String tableId, String waiterId) {
        notEmpty(tableId, "productCreationRequest.productName can not be null or empty");
        notEmpty(waiterId, "productCreationRequest.productName can not be null or empty");
        final RestaurantTable restaurantTable = restaurantTableRepository.getOne(tableId);
        final ApiUser waiter = apiUserRepository.getOne(waiterId);
        restaurantTable.setWaiter(waiter);
        return restaurantTableRepository.save(restaurantTable);
    }
}
