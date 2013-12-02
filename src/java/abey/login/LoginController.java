package abey.login;

import abey.AbstractController;
import abey.services.UtilisateurService;
import abey.entities.Utilisateur;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toinou
 */
@ManagedBean
@ViewScoped
public class LoginController extends AbstractController implements Serializable{

    @ManagedProperty(value = "#{identifiants}")
    private Identifiants identifiants;

    @ManagedProperty(value = "#{utilisateurService}")
    private UtilisateurService utilisateurService;

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
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
            System.out.println("Unable to login:");
        }
        return outcome;
    }

    public void setIdentifiants(Identifiants identifiants) {
        this.identifiants = identifiants;
    }

    public String logout() {
        utilisateurSession.setUtilisateur(null);
        return null;
    }
}
