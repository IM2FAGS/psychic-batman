/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import abey.entities.Utilisateur;
import abey.services.UtilisateurService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author toinou
 */
@ManagedBean
@SessionScoped
public class FillUtilisateurs implements Serializable {

    @EJB
    private UtilisateurService utilisateurService;
    //private abey.facades.UtilisateurFacade utilEjbFacade;

    public void get_util() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail("ab" + "@cd.ef");
        utilisateur.setNom("a");
        utilisateur.setPass("a");
        utilisateur.setSalt("salt");
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 0, (1) % 28);
        Date d = cal.getTime();
        utilisateur.setDateNaissance(d);
        utilisateurService.create(utilisateur);
        for (int i = 0; i < 40; i++) {
            utilisateur = new Utilisateur();
            utilisateur.setMail("ab" + i + "@cd.ef");
            utilisateur.setNom(i + "abcde");
            utilisateur.setPass("pass");
            utilisateur.setSalt("salt");
            cal = Calendar.getInstance();
            cal.set(2010, 0, (i + 1) % 28);
            d = cal.getTime();
            utilisateur.setDateNaissance(d);
            utilisateurService.create(utilisateur);
        }
    }
}
