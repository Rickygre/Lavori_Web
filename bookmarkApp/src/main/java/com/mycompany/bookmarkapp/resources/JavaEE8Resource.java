package com.mycompany.bookmarkapp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/ping")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping è ok")
                .build();
    }
}
