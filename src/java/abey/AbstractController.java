/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import abey.entities.Utilisateur;
import abey.login.UtilisateurSession;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author toinou
 */
public class AbstractController {

    @ManagedProperty(value = "#{utilisateurSession}")
    protected UtilisateurSession utilisateurSession;

    public Utilisateur getUtilisateurConnecte() {
        Utilisateur user = null;
        if (utilisateurSession != null) {
            user = utilisateurSession.getUser();
        }
        return user;
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

}
