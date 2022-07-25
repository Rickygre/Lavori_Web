package airhacks.service.control;

import airhacks.service.entity.Segnalibro;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Greggio Riccardo
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Segnalibrostore {

    @PersistenceContext
    EntityManager em;
    
     @Inject
    Etichettastore etichettastore;

    //creo un segnalibro
    public Segnalibro create(Segnalibro entity) {

        return em.merge(entity);
    }

    //carica lista segnalibri presenti nel database
    public List<Segnalibro> all() {
        return em.createQuery("select e from Segnalibro e order by e.utente", Segnalibro.class).getResultList();
    }

    //salva segnalibro
    public Segnalibro save(Segnalibro entity) {
        return em.merge(entity);
    }
    
    
    //trova segnalibro con suo id 
    public Optional<Segnalibro> find(Long id) {
        Segnalibro found = em.find(Segnalibro.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    //trova segnalibro con id di un utente
    public List<Segnalibro> findByUtente(Long id) {
        return em.createQuery("select e from Segnalibro e where e.utente.id= :id ", Segnalibro.class)
                .setParameter("id", id)
                .getResultList();
    }

    //elimina Segnalibro tramite suo id
    public void eliminaBook(Long id) {
        em.remove(em.getReference(Segnalibro.class, id));
        System.out.println("Segnalibro Cancellato");
    }

}
