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
        for (int i = 0; i < 40; i++) {
            try {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setMail("ab" + i + "@cd.ef");
                utilisateur.setNom(i + "abcde");
                String salt = Salt.newSalt();
                utilisateur.setSalt(salt);
                String passCrypte = Salt.hashPassword("pass", salt);
                utilisateur.setPass(passCrypte);
                Calendar cal = Calendar.getInstance();
                cal.set(2010, 0, (i + 1) % 28);
                Date d = cal.getTime();
                utilisateur.setDateNaissance(d);
                utilisateurService.create(utilisateur);
            } catch (Exception e) {

            }
        }
    }
}
