
package airhacks.service.adapter;

import airhacks.service.control.Segnalibrostore;
import airhacks.service.entity.Segnalibro;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Greggio Riccardo
 */



public class SegnalibroTypeAdapter implements JsonbAdapter<Segnalibro, JsonObject>{
    
    
   @Inject
    Segnalibrostore segnalibrostore;

    @Override
    public JsonObject adaptToJson(Segnalibro entity) throws Exception {
        return entity.toJsonSlice();
    }

    @Override
    public Segnalibro adaptFromJson(JsonObject json) throws Exception {
        if (!json.containsKey("id")) {
            return null;
        }
        return segnalibrostore.find(json.getJsonNumber("id").longValue()).orElseThrow(() -> new NotFoundException("BkmTypeAdapter.adaptFromJson not found"));
    } 

    
    
}
