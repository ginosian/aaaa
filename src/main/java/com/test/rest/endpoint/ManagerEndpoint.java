package com.test.rest.endpoint;

import com.test.rest.endpoint.dto.TableCreationRequestDto;
import com.test.rest.endpoint.dto.TableCreationResponceDto;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/manager")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ManagerEndpoint {

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @POST
    @Path("/create-table")
    TableCreationResponceDto createTable(final TableCreationRequestDto tableCreationRequestDto);

}
