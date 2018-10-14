package com.test.service.manager;

import com.test.service.ProductCreationRequest;
import com.test.service.TableCreationRequest;

public interface ManagerService {

    void createTable(TableCreationRequest tableCreationRequest);
    void createProduct(ProductCreationRequest productCreationRequest);
    void assignTableToWaiter(String tableId, String waiterId);
}
