package abey.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toinou
 */
public abstract class AbstractService<T> {
    
    @PersistenceContext
    protected EntityManager em;
    
    public void create(T instance) {
        em.persist(instance);
    }
    
    public void remove(T instance) {
        em.remove(em.merge(instance));
    }
    
    public void edit(T instance) {
        em.merge(instance);
    }
    
}
