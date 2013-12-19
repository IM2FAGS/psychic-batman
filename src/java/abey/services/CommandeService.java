package abey.services;

import abey.entities.Commande;
import abey.entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author disavinr
 */
@Stateless
public class CommandeService extends AbstractService<Commande> {

    public CommandeService() {
        super(Commande.class);
    }

    public List<Commande> getCommandes(Utilisateur utilisateur) {
        TypedQuery<Commande> query = em.createNamedQuery("Commande.getCommandes", Commande.class);
        query.setParameter(1, utilisateur);

        return query.getResultList();
    }

}
