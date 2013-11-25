/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.services;

import abey.entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author toinou
 */
@Named("produitService")
@Stateless
public class ProduitService extends AbstractService<Produit> {
    
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
    
}
