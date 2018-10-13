package com.test.rest.endpoint.impl;

import com.test.mapper.ModelMapper;
import com.test.rest.endpoint.ManagerEndpoint;
import com.test.rest.endpoint.dto.TableCreationRequestDto;
import com.test.rest.endpoint.dto.TableCreationResponceDto;
import com.test.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerEndpointImpl implements ManagerEndpoint {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TableCreationResponceDto createTable(TableCreationRequestDto tableCreationRequestDto) {
        return managerService.createTable(modelMapper.map(tableCreationRequestDto, TableCreationRequest.class));
    }
}
