package airhacks.service.entity;

import airhacks.service.adapter.UtenteTypeAdapter;
import java.util.Set;
import java.util.TreeSet;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Greggio Riccardo
 */
@Entity
@Table(name = "t_segnalibri")
public class Segnalibro extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String link;

    @JsonbProperty(value = "descr")
    @NotBlank
    @Column(nullable = false, length = 400, name = "descrizione")
    private String descrizione;

    @JsonbTransient
    @ManyToMany
    @JoinTable(name = "segnalibro_etichetta",
            joinColumns = @JoinColumn(name = "segnalibro_id"),
            inverseJoinColumns = @JoinColumn(name = "etichetta_id"))
    private Set<Etichetta> etichette = new TreeSet<>();

    @Column(nullable = false)
    private boolean condiviso;

    @ManyToOne(optional = false)
    @JsonbTypeAdapter(UtenteTypeAdapter.class)
    private Utente utente;

    public Segnalibro() {

    }

    public Segnalibro(String link, String descrizione, boolean condiviso, Utente utente) {
        this.link = link;
        this.descrizione = descrizione;
        this.condiviso = condiviso;
        this.utente = utente;
    }

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

    public Set<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(Set<Etichetta> etichette) {
        this.etichette = etichette;
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
        return "Segnalibro{" + "link=" + link + ", descrizione=" + descrizione + ", etichetta=" + etichette + ", condiviso=" + condiviso + ", utente=" + utente + '}';
    }

    public JsonObject toJsonSlice() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("description", this.descrizione)
                .build();
    }

   

   

}
