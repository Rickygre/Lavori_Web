
package airhacks.service.control;

import airhacks.service.SecurityEncoding.SecurityEncoding;
import airhacks.service.entity.Utente;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 *
 * @author tss
 */


@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Utentestore {
    
    @PersistenceContext
    EntityManager em;
    
    
    //restituisce la lista di tutti gli utenti presenti nel database
    public List<Utente>all(){
        return em.createQuery("select e from Utente e order by e.nome",Utente.class)
                .getResultList();
        
    }
    
    public Utente create(Utente entity) {
        entity.setPassword(SecurityEncoding.shaHash(entity.getPassword()));
        return em.merge(entity);
    }
    
    
    public Optional<Utente> find (Long id){
        Utente found = em.find(Utente.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
    
}
