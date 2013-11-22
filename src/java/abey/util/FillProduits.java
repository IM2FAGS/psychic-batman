/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@ManagedBean
@Named("fillProduits")
@SessionScoped
public class FillProduits implements Serializable {

    @EJB
    private abey.ProduitFacade prodEjbFacade;

    public void get_util() {
        for (int i = 0; i < 40; i++) {
            abey.Produit produit = new abey.Produit();
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
