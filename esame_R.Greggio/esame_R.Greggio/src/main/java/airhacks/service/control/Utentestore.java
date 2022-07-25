package airhacks.service.control;

import airhacks.service.SecurityEncoding.SecurityEncoding;
import airhacks.service.boundary.Credential;
import airhacks.service.entity.Utente;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Greggio Riccardo
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Utentestore {

    @PersistenceContext
    EntityManager em;

    //restituisce la lista di tutti gli utenti presenti nel database
    public List<Utente> all() {
        return em.createQuery("select e from Utente e order by e.nome", Utente.class)
                .getResultList();

    }

    public Utente create(Utente entity) {
        entity.setPassword(SecurityEncoding.shaHash(entity.getPassword()));
        return em.merge(entity);
    }

    public Optional<Utente> find(Long id) {
        Utente found = em.find(Utente.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    
    public Optional<Utente> login(Credential credential) {
        try {
            return Optional.of(em.createQuery("select e from Utente e where e.email= :usr and e.password= : pwd", Utente.class)
                    .setParameter("usr", credential.usr) //proprietà usr e valore email dell utente
                    .setParameter("pwd", SecurityEncoding.shaHash(credential.pwd))
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }

    }
  

}
