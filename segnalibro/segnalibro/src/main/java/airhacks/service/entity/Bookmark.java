package airhacks.service.entity;

import airhacks.service.Adapter.UtenteTypeAdapter;
import java.util.Set;
import java.util.TreeSet;
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
 * @author riccardo
 */
@Entity
@Table(name = "t_bookmarks")
public class Bookmark extends BaseEntity {

    @JsonbProperty
    @NotBlank
    @Column(nullable = false)
    private String link;

    @JsonbProperty
    @NotBlank
    @Column(nullable = false, length = 400)
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

    public Bookmark() {

    }

    public Bookmark(String link, String descrizione, boolean condiviso, Utente utente) {
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
        return "Bookmark{" + "link=" + link + ", descrizione=" + descrizione + ", etichetta=" + etichette + ", condiviso=" + condiviso + ", utente=" + utente + '}';
    }

}
