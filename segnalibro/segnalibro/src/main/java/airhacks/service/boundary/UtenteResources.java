package airhacks.service.boundary;

import airhacks.service.JWTManager;
import airhacks.service.control.Utentestore;
import airhacks.service.entity.Utente;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Path("/utenti")
public class UtenteResources {

    @Inject
    Utentestore utenteStore;
    
     @Inject
    JWTManager jwtmanager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utente> all() {
        return utenteStore.all();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid Utente entity) {
        utenteStore.create(entity);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public JsonObject login(@Valid Credential credential) {
        Utente ut = utenteStore.login(credential).orElseThrow(() -> new NotAuthorizedException(
                Response.status(Response.Status.UNAUTHORIZED).build()));
        
        String jwt = jwtmanager.generate(ut);
        return Json.createObjectBuilder()
                .add("jwt", jwt)
                .build();

    }
}
