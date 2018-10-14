package com.test.service;

import lombok.Data;

@Data
public class TableUpdateRequest {

    private String tableId;
    private String tableName;
    private String assignedWaiterId;
}
