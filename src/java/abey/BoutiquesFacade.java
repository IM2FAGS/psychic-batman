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
public class BoutiquesFacade extends AbstractFacade<Boutiques> {
    @PersistenceContext(unitName = "abeyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoutiquesFacade() {
        super(Boutiques.class);
    }
    
}
