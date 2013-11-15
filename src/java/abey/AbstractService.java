/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toinou
 */
public abstract class AbstractService {
    
    @PersistenceContext
    protected EntityManager em;
    
    
}
