package abey.login;

import abey.AbstractController;
import abey.services.UtilisateurService;
import abey.entities.Utilisateur;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toinou
 */
@ManagedBean
@ViewScoped
public class LoginController extends AbstractController implements Serializable {

    @ManagedProperty(value = "#{utilisateurSession}")
    private UtilisateurSession utilisateurSession;

    @ManagedProperty(value = "#{identifiants}")
    private Identifiants identifiants;

    @EJB
    private UtilisateurService utilisateurService;

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurSession.getUtilisateur();
    }

    public String login() {
        String outcome = null;
        try {
            String nom = identifiants.getNom();
            String pass = identifiants.getPass();
            Utilisateur utilisateur = utilisateurService.getUtilisateur(nom, pass);
            if (utilisateur == null) {
                outcome = "/login/errorLogin";
            } else {
                outcome = "/index";
                utilisateurSession.setUtilisateur(utilisateur);
            }
        } catch (Exception e) {
            outcome = "/login/errorLogin";
        }
        return outcome;
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    public void setIdentifiants(Identifiants identifiants) {
        this.identifiants = identifiants;
    }

    public String logout() {
        utilisateurSession.setUtilisateur(null);
        return null;
    }
}
