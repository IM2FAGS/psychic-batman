/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@Named("fillProduits")
@SessionScoped
public class fill_produits implements Serializable {

    @EJB
    private abey.ProduitsFacade prodEjbFacade;

    public void get_util() {
        for (int i = 0; i < 40; i++) {
            abey.Produits produit = new abey.Produits();
            produit.setNom("a"+i);
            produit.setDescription("b"+i);
            Calendar cal = Calendar.getInstance();
            cal.set(2010, 0, (i+1) % 28);
            Date d = cal.getTime();
            produit.setDateDebut(d);
            produit.setDuree(2);
            produit.setPrix(i);
            prodEjbFacade.create(produit);
        }
    }
}
