package airhacks.service.control;

import airhacks.service.entity.Etichetta;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Greggio Riccardo
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Etichettastore {

    @PersistenceContext
    EntityManager em;

    public List<Etichetta> all() {
        return em.createQuery("select e from Etichetta e order by e.name", Etichetta.class)
                .getResultList();

    }

    public Etichetta create(Etichetta entity) {
        entity.getName();
        return em.merge(entity);
    }

    public Optional<Etichetta> find(Long id) {
        Etichetta found = em.find(Etichetta.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

}
