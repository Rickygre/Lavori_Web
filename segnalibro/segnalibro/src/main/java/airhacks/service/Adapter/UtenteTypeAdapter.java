package airhacks.service.Adapter;

import airhacks.service.control.Utentestore;
import airhacks.service.entity.Utente;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author riccardo
 */
public class UtenteTypeAdapter implements JsonbAdapter<Utente, JsonObject> {

    @Inject
    Utentestore utenteStore;

    @Override
    public JsonObject adaptToJson(Utente entity) throws Exception {
        return entity.toJsonByCreatedSegnalibro();
    }

    @Override
    public Utente adaptFromJson(JsonObject json) throws Exception {
        if (!json.containsKey("id")) {
            return null;
        }
        return utenteStore.find(json.getJsonNumber("id").longValue()).orElseThrow(() -> new NotFoundException("..."));
    }
}


