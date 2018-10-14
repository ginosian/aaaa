package com.test.rest.endpoint;


import com.test.rest.endpoint.dto.InfoDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/waiter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface WaiterEndpoint {

    @GET
    @Path("")
    InfoDto info();
}
