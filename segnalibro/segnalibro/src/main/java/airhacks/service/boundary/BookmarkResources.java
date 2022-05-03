
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
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@Valid Bookmark entity) {
        bookmarkstore.create(entity);
    }
    
    
    @DELETE
    @Path("{id}")   
    public void eliminaBook(@PathParam("id") Long id){
        System.out.println("Cancelare Id: " + String.valueOf(id));
        Bookmark found = bookmarkstore.find(id).orElseThrow(() -> new NotFoundException("bookmark non trovato. id="+ id));
        System.out.println("Cancelare Id Trovato: " + String.valueOf(found.getId()));
        bookmarkstore.eliminaBook(found.getId());
                
        
    }
    
    
}
