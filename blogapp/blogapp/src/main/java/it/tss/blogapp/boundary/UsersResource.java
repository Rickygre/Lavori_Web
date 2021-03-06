package it.tss.blogapp.boundary;

import it.tss.blogapp.JWTManager;
import it.tss.blogapp.control.PostStore;
import it.tss.blogapp.control.UserStore;
import it.tss.blogapp.entity.Post;
import it.tss.blogapp.entity.User;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Path("/users")
public class UsersResource {

    @Inject
    private UserStore userstore;

    @Inject
    private PostStore poststore;

    @Inject
    private JWTManager jwtmanager;
    
   

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> all() {
        return userstore.all();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid User entity) {
        userstore.create(entity);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") Long id) {
        return userstore.find(id).orElseThrow(() -> new NotFoundException("user non trovato. id=" + id));
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        User found = userstore.find(id).orElseThrow(() -> new NotFoundException("user non trovato. id=" + id));
        userstore.delete(found.getId());
    }

    //visualizza i post di un utente
    @GET
    @Path("{id}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> posts(@PathParam("id") Long id) {
        return poststore.byUser(id);
    }

    /**
     *
     * crea i post di un determinato user
     **/
    @POST
    @Path("{id}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public void createPost(@PathParam("id") Long id, @Valid Post Entity) {
        User found = userstore.find(id).orElseThrow(() -> new NotFoundException("user non trovato. id=" + id));
        Entity.setAuthor(found);
        poststore.save(Entity);

    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public JsonObject login(@Valid Credential credential) {
        User u = userstore.login(credential).orElseThrow(() -> new NotAuthorizedException(
                Response.status(Response.Status.UNAUTHORIZED).build()));
        String jwt = jwtmanager.generate(u);
        return Json.createObjectBuilder()
                .add("jwt", jwt)
                .build();

    }

}
