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
        Utilisateur utilisateur = null;
        if (utilisateurSession != null) {
            utilisateur = utilisateurSession.getUtilisateur();
        }
        return utilisateur;
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

}
