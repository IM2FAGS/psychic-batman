package abey.services;

import abey.entities.Utilisateur;
import abey.util.Salt;
import java.security.NoSuchAlgorithmException;
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

	public UtilisateurService() {
		super(Utilisateur.class);
	}

    public Utilisateur getUtilisateur(String nom, String pass) throws NoSuchAlgorithmException {
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.getByNom",
                                                        Utilisateur.class);
        query.setParameter(1, nom);
        List<Utilisateur> utilisateurs = query.getResultList();
        if (utilisateurs != null && utilisateurs.size() == 1) {
            Utilisateur utilisateur = utilisateurs.get(0);
            String salt = utilisateur.getSalt();
            if (Salt.checkPassword(pass, salt, utilisateur.getPass())) {
                return utilisateur;
            } else {
                return null;
            }
        }
        return null;
    }
}
