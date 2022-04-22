
package airhacks.service.boundary;

import airhacks.service.control.Utentestore;
import airhacks.service.entity.Utente;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tss
 */

@Path("/utenti")
public class UtenteResources {
    
    @Inject
    Utentestore utenteStore;
    
  
    
    
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
    
    
}
