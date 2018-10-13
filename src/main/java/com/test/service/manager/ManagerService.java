package com.test.service.manager;

public interface ManagerService {
    
    void createTable(TableCreationRequest tableCreationRequest);
    void createProduct(ProductCreationRequest productCreationRequest);
    void assignTableToWaiter(String tableId, String waiterId);
}
