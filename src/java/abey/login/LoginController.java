/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.login;

import abey.UtilisateurService;
import abey.Utilisateur;
import abey.UtilisateurController;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
            Utilisateur user = userService.getUtilisateurs(username, password);
            if (user == null) {
                outcome = "/login/errorLogin";
            } else {
                outcome = "/index";
                userSession.setUser(user);
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
        userSession.setUser(null);
//        activeUsers.remove(getLoggedInUser());
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return null;
    }
//    public void setActiveUsers(ActiveUsers activeUsers) {
//        this.activeUsers = activeUsers;
//    }
}
