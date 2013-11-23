/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.facades;

import abey.entities.Produit;
import abey.facades.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toinou
 */
@Stateless
@Deprecated
public class ProduitFacade extends AbstractFacade<Produit> {
    @PersistenceContext(unitName = "abeyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
    
}
