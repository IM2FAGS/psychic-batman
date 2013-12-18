package abey;

import abey.entities.Utilisateur;
import abey.services.UtilisateurService;
import abey.util.JsfUtil;
import abey.util.Salt;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerUtilisateurController extends AbstractController {
    
    private Utilisateur utilisateur;
    
    @EJB
    private UtilisateurService utilisateurService;

    public Utilisateur getUtilisateur() {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public String creer() {
        try {
            String salt = Salt.newSalt();
            utilisateur.setSalt(salt);
            String passCrypte = Salt.hashPassword(utilisateur.getPass(), salt);
            utilisateur.setPass(passCrypte);
            utilisateurService.create(utilisateur);
            setUtilisateurConnecte(utilisateur);
            utilisateur = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("UserCreated"));
            return "/index";
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("UserCreatedError"));
            return null;
        }
    }
    
    public String initEditer() {
        utilisateur = getUtilisateurConnecte();
        return "/utilisateurs/Edit";
    }
    
    public String editer() {
        try {
            if (utilisateur.getPass() != null && !utilisateur.getPass().equals("")) {
                String salt = Salt.newSalt();
                utilisateur.setSalt(salt);
                String passCrypte = Salt.hashPassword(utilisateur.getPass(), salt);
                utilisateur.setPass(passCrypte);
            }
            utilisateurService.edit(utilisateur);
            setUtilisateurConnecte(utilisateur);
            utilisateur = null;
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("UserUpdated"));
            return "/utilisateurs/Profil";
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("UserUpdatedError"));
            return null;
        }
    }
    
}
