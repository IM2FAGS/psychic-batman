package abey.login;

import abey.AbstractController;
import abey.services.UtilisateurService;
import abey.entities.Utilisateur;
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
public class LoginController extends AbstractController {

    @ManagedProperty(value = "#{identifiants}")
    private Identifiants identifiants;

    @EJB
    private UtilisateurService utilisateurService;

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
                setUtilisateurConnecte(utilisateur);
            }
        } catch (Exception e) {
            outcome = "/login/errorLogin";
        }
        return outcome;
    }

    public void setIdentifiants(Identifiants identifiants) {
        this.identifiants = identifiants;
    }

    public String logout() {
        setUtilisateurConnecte(null);
        return null;
    }
}
