/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.login;

import abey.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@ManagedBean
@Named("userSession")
@SessionScoped
public class UserSession implements Serializable {
    
//    @Inject
//    private ActiveUsers activeUsers;
    
    private Utilisateur user;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
//    @PreDestroy
//    public void release(){
//        activeUsers.remove(user);
//    }
    
}
