/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
}
