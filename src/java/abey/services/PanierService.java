package abey.services;

import abey.entities.Panier;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author disavinr
 */
@Stateless
public class PanierService extends AbstractService<Panier> {

	public PanierService() {
		super(Panier.class);
	}
    
    public List<Panier> recherchePaniers(String recherche){
        TypedQuery<Panier> query = em.createNamedQuery("Panier.recherche",
                                                        Panier.class);
        query.setParameter(1, "%" + recherche.replace("%", "\\%") + "%");
        List<Panier> produits = query.getResultList();
        if (produits != null && !produits.isEmpty()) {
            return produits;
        }
        return new ArrayList<>();
    }
    
    public List<Panier> getCoupsDeCoeur() {
        return findAll();
    }
    
}
