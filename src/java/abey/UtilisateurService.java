package abey;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author toinou
 */
@Named("utilisateurService")
@Stateless
public class UtilisateurService extends AbstractService {

    public Utilisateur getUtilisateurs(String username, String password) {
        System.out.println("blabla");
        System.out.println(em);
        System.out.println("456");
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.findUserPass",
                                                        Utilisateur.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        System.out.println("123");
        List<Utilisateur> users = query.getResultList();
        System.out.println("users = " + users.size());
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
