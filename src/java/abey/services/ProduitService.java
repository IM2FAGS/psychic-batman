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
    
    public List<Produit> rechercheProduits(String recherche){
        TypedQuery<Produit> query = em.createNamedQuery("Produit.recherche",
                                                        Produit.class);
        query.setParameter(1, "%" + recherche.replace("%", "\\%") + "%");
        List<Produit> produits = query.getResultList();
        if (produits != null && !produits.isEmpty()) {
            return produits;
        }
        return new ArrayList<>();
    }
    
    public List<Produit> getCoupsDeCoeur() {
        return findAll();
    }
    
}
