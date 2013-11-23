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
        Utilisateur user = new Utilisateur();
        user.setMail("ab" + "@cd.ef");
        user.setNom("a");
        user.setPass("a");
        user.setSalt("salt");
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 0, (1) % 28);
        Date d = cal.getTime();
        user.setDateNaissance(d);
        utilisateurService.create(user);
        for (int i = 0; i < 40; i++) {
            user = new Utilisateur();
            user.setMail("ab" + i + "@cd.ef");
            user.setNom(i + "abcde");
            user.setPass("pass");
            user.setSalt("salt");
            cal = Calendar.getInstance();
            cal.set(2010, 0, (i + 1) % 28);
            d = cal.getTime();
            user.setDateNaissance(d);
            utilisateurService.create(user);
        }
    }
}
