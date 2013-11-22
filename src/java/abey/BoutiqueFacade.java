/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toinou
 */
@Stateless
public class BoutiqueFacade extends AbstractFacade<Boutique> {
    @PersistenceContext(unitName = "abeyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoutiqueFacade() {
        super(Boutique.class);
    }
    
}
