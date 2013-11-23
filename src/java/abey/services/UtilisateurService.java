package abey.services;

import abey.entities.Utilisateur;
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
public class UtilisateurService extends AbstractService<Utilisateur> {

    public Utilisateur getUtilisateur(String nom, String pass) {
        System.out.println("blabla");
        System.out.println(em);
        System.out.println("456");
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.rechercheNomPass",
                                                        Utilisateur.class);
        query.setParameter(1, nom);
        query.setParameter(2, pass);
        System.out.println("123");
        List<Utilisateur> users = query.getResultList();
        System.out.println("users = " + users.size());
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
