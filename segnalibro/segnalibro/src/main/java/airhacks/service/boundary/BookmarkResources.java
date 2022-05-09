
package airhacks.service.boundary;

import airhacks.service.control.Bookmarkstore;
import airhacks.service.entity.Bookmark;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author riccardo
 */

@Path("/bookmarks")
public class BookmarkResources {
    
    @Inject
    Bookmarkstore bookmarkstore;
    
  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bookmark> all (){
        return bookmarkstore.all();
    }    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bookmark> findByUtente(@PathParam("id") Long id) {
        //return bookmarkstore.find(id).orElseThrow(() -> new NotFoundException());
        return bookmarkstore.findByUtente(id);
    }
    
    
    
    /*@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bookmark find(@PathParam("id") Long id) {
        return bookmarkstore.find(id).orElseThrow(() -> new NotFoundException());
        
    }*/
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid Bookmark entity) {
        bookmarkstore.create(entity);
    }
    
    
    @DELETE
    @Path("{id}")   
    public void eliminaBook(@PathParam("id") Long id){
        System.out.println("Cancellare Id: " + String.valueOf(id));
        Bookmark found = bookmarkstore.find(id).orElseThrow(() -> new NotFoundException("bookmark non trovato. id="+ id));
        System.out.println("Cancellare Id Trovato: " + String.valueOf(found.getId()));
        bookmarkstore.eliminaBook(found.getId());
                
        
    }
    
    
}
