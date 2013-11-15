/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import abey.login.UserSession;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author toinou
 */
public class AbstractController {

    @ManagedProperty(value = "#{userSession}")
    protected UserSession userSession;

    protected Utilisateurs getLoggedInUser() {
        Utilisateurs user = null;
        if (userSession != null) {
            user = userSession.getUser();
        }
        return user;
    }

    public String getLoggedInUserName() {
        String nom = null;
        if (userSession != null) {
            if (userSession.getUser() != null) {
                nom = userSession.getUser().getNom();
            }
        }
        return nom;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
