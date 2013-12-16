package abey.services;

import abey.entities.Commande;
import java.util.ArrayList;
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

    public List<Commande> rechercheCommandes(String recherche) {
        TypedQuery<Commande> query = em.createNamedQuery("Commande.recherche",
                Commande.class);
        query.setParameter(1, "%" + recherche.replace("%", "\\%") + "%");
        List<Commande> boutiques = query.getResultList();
        if (boutiques != null && !boutiques.isEmpty()) {
            return boutiques;
        }
        return new ArrayList<>();
    }

}
