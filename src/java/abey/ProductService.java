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
//@ManagedBean
@Named("productService")
@Stateless
public class ProductService extends AbstractService{
    
    public List<Produits> getProduits(String patern){
        TypedQuery<Produits> query = em.createNamedQuery("Produits.findProduct",
                                                        Produits.class);
        query.setParameter(1, patern);
        System.out.println("123");
        List<Produits> produits = query.getResultList();
        System.out.println("produits = " + produits.size());
        if (produits != null && !produits.isEmpty()) {
            System.out.println("on a des produits");
            return produits;
        }
        return new ArrayList<>();
    }
    
}
