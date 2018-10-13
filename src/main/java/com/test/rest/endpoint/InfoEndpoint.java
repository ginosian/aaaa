package com.test.rest.endpoint;

import com.test.rest.endpoint.dto.InfoDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InfoEndpoint {

    @GET
    @Path("")
    InfoDto info();
}
