package it.tss.blogapp.control;

import it.tss.blogapp.entity.Post;
import it.tss.blogapp.entity.Tag;
import it.tss.blogapp.entity.User;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author tss
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    TagStore tagStore;

    public List<Post> all() {
        return em.createQuery("select e from Post e order by e.created DESC")
                .getResultList();
    }

    public List<Post> byUser(Long userId) {
        return em.createQuery("select e from Post e where e.author.id = :userId order by e.created DESC", Post.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Optional<Post> find(Long id) {
        Post found = em.find(Post.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public Post save(Post entity) {
        return em.merge(entity);

    }

    public void delete(Long id) {
        em.remove(em.getReference(User.class, id));
    }

    public void addTag(Post found, String tag) {
        Post toupdate = find(found.getId()).get();
        Tag saved = tagStore.saveIfNotExists(tag);
        toupdate.getTags().add(saved);
        save(toupdate);
    }

    public void removeTag(Long id, String tag) {
        final Post toupdate = find(id).get();
        Optional<Tag> found = tagStore.byName(tag);
        if (found.isPresent()) {
            toupdate.getTags().remove(found.get());
            save(toupdate);
        }
    }

}
