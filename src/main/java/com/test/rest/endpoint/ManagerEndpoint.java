package com.test.rest.endpoint;

import com.test.mapper.ModelMapper;
import com.test.rest.endpoint.dto.TableCreationRequestDto;
import com.test.rest.endpoint.dto.TableCreationResponceDto;
import com.test.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/manager")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManagerEndpoint {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ModelMapper modelMapper;

    @POST
    @Path("/create-table")
    public TableCreationResponceDto createTable(final TableCreationRequestDto tableCreationRequestDto){
        return managerService.createTable(modelMapper.map(tableCreationRequestDto, TableCreationRequest.class));
    }

}
