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
@Named("fillDb")
@SessionScoped
public class fill_db implements Serializable {

    @EJB
    private abey.UtilisateursFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public void get_util() {
        for (int i = 0; i < 40; i++) {
            abey.Utilisateurs user = new abey.Utilisateurs();
            user.setMail("ab"+i+"@cd.ef");
            user.setNom(i+"abcde");
            user.setPass("pass");
            user.setSalt("salt");
            Calendar cal = Calendar.getInstance();
            cal.set(2010, 0, (i+1) % 28);
            Date d = cal.getTime();
            user.setDateNaissance(d);
            ejbFacade.create(user);
        }
    }
}
