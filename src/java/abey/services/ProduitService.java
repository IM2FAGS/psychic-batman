package abey.services;

import abey.entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author toinou
 */
@Stateless
public class ProduitService extends AbstractService<Produit> {

	public ProduitService() {
		super(Produit.class);
	}
    
    public List<Produit> getCoupsDeCoeur() {
        return findAll();
    }
    
}
