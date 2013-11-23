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
@Named("fillDb")
@SessionScoped
public class FillDb implements Serializable {

    @EJB
    private abey.UtilisateurFacade utilEjbFacade;

    public void get_util() {
        abey.Utilisateur user = new abey.Utilisateur();
        user.setMail("ab" + "@cd.ef");
        user.setNom("a");
        user.setPass("a");
        user.setSalt("salt");
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 0, (1) % 28);
        Date d = cal.getTime();
        user.setDateNaissance(d);
        utilEjbFacade.create(user);
        for (int i = 0; i < 40; i++) {
            user = new abey.Utilisateur();
            user.setMail("ab" + i + "@cd.ef");
            user.setNom(i + "abcde");
            user.setPass("pass");
            user.setSalt("salt");
            cal = Calendar.getInstance();
            cal.set(2010, 0, (i + 1) % 28);
            d = cal.getTime();
            user.setDateNaissance(d);
            utilEjbFacade.create(user);
        }
    }
}
