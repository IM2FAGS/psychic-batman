/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import abey.entities.Produit;
import abey.services.ProduitService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@ManagedBean
@SessionScoped
public class FillProduits implements Serializable {

    @EJB
    private ProduitService produitService;
    //private abey.facades.ProduitFacade prodEjbFacade;

    public void get_util() {
        for (int i = 0; i < 40; i++) {
            Produit produit = new Produit();
            produit.setNom("a"+i);
            produit.setDescription("b"+i);
            produitService.create(produit);
        }
    }
}
