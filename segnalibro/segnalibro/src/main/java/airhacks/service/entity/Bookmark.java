
package airhacks.service.entity;

import airhacks.service.Adapter.UtenteTypeAdapter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author riccardo
 */
@Entity
@Table(name="t_bookmarks")
public class Bookmark extends BaseEntity{
    
    @NotBlank
    @Column(nullable=false)
    private String link;
    
    @NotBlank
    @Column(nullable=false , length=400)
    private String descrizione;
    
    @NotBlank
    @Column(nullable=false)
    private String etichetta;
    
    
    @Column(nullable=false)
    private boolean condiviso;
    

    
     @ManyToOne(optional = false)
    @JsonbTypeAdapter(UtenteTypeAdapter.class)
    private Utente utente;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(String etichetta) {
        this.etichetta = etichetta;
    }

    public boolean isCondiviso() {
        return condiviso;
    }

    public void setCondiviso(boolean condiviso) {
        this.condiviso = condiviso;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Bookmark{" + "link=" + link + ", descrizione=" + descrizione + ", etichetta=" + etichetta + ", condiviso=" + condiviso + ", utente=" + utente + '}';
    }
     
     
     public JsonObject toJsonByCreatedSegnalibro (){
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("email", this.link)
                .build();
    }
    
    
}
