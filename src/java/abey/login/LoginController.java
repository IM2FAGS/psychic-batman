/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.login;

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
public class LoginController extends abey.AbstractController implements Serializable{

    @ManagedProperty(value = "#{identifiants}")
    private Identifiants identifiants;
//    @ManagedProperty(value = "#{activeUsers}")
//    private ActiveUsers activeUsers;
    @ManagedProperty(value = "#{utilisateurService}")
    private UtilisateurService userService;

    public void setUserService(UtilisateurService userService) {
        this.userService = userService;
    }

    public String login() {
        String outcome = null;
        try {
            String username = identifiants.getNom();
            String password = identifiants.getPass();
            System.out.println("user = " + username + "  password = " + password);
            Utilisateur user = userService.getUtilisateur(username, password);
            if (user == null) {
                outcome = "/login/errorLogin";
            } else {
                outcome = "/index";
                utilisateurSession.setUser(user);
            }
        } catch (Exception e) {
            outcome = "/login/errorLogin";
            System.out.println("Unable to login:");
//            System.out.println(e);
        }
        return outcome;
    }

    public void setIdentifiants(Identifiants identifiants) {
        this.identifiants = identifiants;
    }

    public String logout() {
        utilisateurSession.setUser(null);
//        activeUsers.remove(getLoggedInUser());
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return null;
    }
//    public void setActiveUsers(ActiveUsers activeUsers) {
//        this.activeUsers = activeUsers;
//    }
}
