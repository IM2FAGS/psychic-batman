package abey.facades;

import abey.entities.Boutique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toinou
 */
@Stateless
@Deprecated
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
