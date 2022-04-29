package airhacks.service.control;

import airhacks.service.entity.Bookmark;
import airhacks.service.entity.Utente;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author riccardo
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)

public class Bookmarkstore {

    @PersistenceContext
    EntityManager em;

    //creo un segnalibro
    public Bookmark create(Bookmark entity) {
        
        return em.merge(entity);
    }

    //ritorna lista segnalibri presenti nel database
    public List<Bookmark> all() {
        return em.createQuery("select e from Bookmark e order by e.utente", Bookmark.class).getResultList();
    }

    //trova segnalibro
    public Optional<Bookmark> find(Long id) {
        Bookmark found = em.find(Bookmark.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    
    public void eliminaBook(Long id){
        em.remove(em.getReference(Utente.class, id));
    }
    
    
    
}
