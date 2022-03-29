
package it.tss.blogapp.boundary;



import it.tss.blogapp.entity.Comment;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tss
 */
@Path("/comments")
public class CommentsResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment find(@PathParam("id") Long id) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment update(@PathParam("id") Long id, @Valid Comment entity) {
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {
        throw new UnsupportedOperationException();
    }

}