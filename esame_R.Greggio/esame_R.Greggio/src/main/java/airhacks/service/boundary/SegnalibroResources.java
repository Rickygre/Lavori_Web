package airhacks.service.boundary;

import airhacks.service.control.Etichettastore;
import airhacks.service.control.Segnalibrostore;
import airhacks.service.entity.Segnalibro;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Greggio Riccardo
 */
@Path("/segnalibri")
public class SegnalibroResources {

    @Inject
    Segnalibrostore segnalibrostore;
    
    @Inject
    Etichettastore etichettastore;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Segnalibro> all() {
        return segnalibrostore.all();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Segnalibro> findByUtente(@PathParam("id") Long id) {
        return segnalibrostore.findByUtente(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid Segnalibro entity) {
        segnalibrostore.create(entity);
    }

    @DELETE
    @Path("{id}")
    public void eliminaBook(@PathParam("id") Long id) {
        System.out.println("Cancellare Id: " + String.valueOf(id));
        Segnalibro found = segnalibrostore.find(id).orElseThrow(() -> new NotFoundException("Segnalibro non trovato. id=" + id));
        System.out.println("Cancellare Id Trovato: " + String.valueOf(found.getId()));
        segnalibrostore.eliminaBook(found.getId());

    }
    
    /*@PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("utenti")
    public Segnalibro update(@PathParam("id") Long id, @Valid Segnalibro entity) {
        checkOwner(entity.getOwner().getId());
        entity.setId(id);
        return segnalibrostore.save(entity);
    }
    
    
    @PATCH
    @Path("{id}/etichette")
    @Consumes(MediaType.TEXT_PLAIN)
    @RolesAllowed("utenti")
    public void addEtichetta(@PathParam("id") Long id, @NotBlank String tag) {
        Segnalibro found = etichettastore.find(id).orElseThrow(() -> new NotFoundException());
        checkOwner(found.getOwner().getId());
        etichettastore.addEtichetta(found, tag);
    }*/
    
    
    
     

}
