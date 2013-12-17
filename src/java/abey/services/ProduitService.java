package abey.services;

import abey.entities.Produit;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author toinou
 */
@Stateless
public class ProduitService extends AbstractService<Produit> {

	public ProduitService() {
		super(Produit.class);
	}

    public List<Produit> rechercheProduits(String recherche){
        return findInsensitiveLike("nom", recherche);
    }

    public List<Produit> getCoupsDeCoeur() {
        return findAll();
    }
    
}
