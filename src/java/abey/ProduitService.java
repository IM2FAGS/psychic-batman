/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author toinou
 */
@Named("produitService")
@Stateless
public class ProduitService extends AbstractService{
    
    public List<Produit> getProduits(String patern){
        TypedQuery<Produit> query = em.createNamedQuery("Produit.findProduct",
                                                        Produit.class);
        query.setParameter(1, patern);
        System.out.println("123");
        List<Produit> produits = query.getResultList();
        System.out.println("produits = " + produits.size());
        if (produits != null && !produits.isEmpty()) {
            System.out.println("on a des produits");
            return produits;
        }
        return new ArrayList<>();
    }
    
}
