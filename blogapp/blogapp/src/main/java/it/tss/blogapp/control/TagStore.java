
package it.tss.blogapp.control;

import it.tss.blogapp.entity.Tag;
import it.tss.blogapp.entity.Post;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author tss
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class TagStore {

    @PersistenceContext
    EntityManager em;

    public List<Tag> all() {
        return em.createQuery("select e from Tag e order by e.name")
                .getResultList();
    }

    public Tag save(Tag entity) {
        return em.merge(entity);
    }

    public Tag saveIfNotExists(String tag) {
        Optional<Tag> found = byName(tag);
        if (found.isPresent()) {
            return found.get();
        }
        return this.save(new Tag(tag));
    }

    public Optional<Tag> find(Long id) {
        Tag found = em.find(Tag.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public Optional<Tag> byName(String tag) {
        try {
            return Optional.of(em.createQuery("select e from Tag e where TRIM(LOWER(e.name))= :name", Tag.class)
                    .setParameter("name", tag.trim().toLowerCase())
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Set<Tag> byPost(Long postId) {
        return em.createQuery("select e from Post e left join fetch e.tags where e.id= :id ", Post.class)
                .setParameter("id", postId)
                .getSingleResult()
                .getTags();

    }

}