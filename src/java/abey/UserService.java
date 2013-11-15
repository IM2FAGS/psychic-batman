/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author toinou
 */
@Named("userService")
@Stateless
public class UserService extends AbstractService {

    public Utilisateurs getUtilisateurs(String username, String password) {
        System.out.println("blabla");
        System.out.println(em);
        System.out.println("456");
        TypedQuery<Utilisateurs> query = em.createNamedQuery("Utilisateurs.findUserPass",
                                                        Utilisateurs.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        System.out.println("123");
        List<Utilisateurs> users = query.getResultList();
        System.out.println("users = " + users.size());
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
