
package airhacks.service.boundary;

import airhacks.service.control.Etichettastore;
import airhacks.service.entity.Etichetta;
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
 * @author riccardo
 */

@Path("/etichette")
public class EtichettaResources {
    
    @Inject
    Etichettastore etichettaStore;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etichetta> all() {
       return etichettaStore.all();
    }
   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid Etichetta entity) {
        etichettaStore.create(entity);
    }
    
}
