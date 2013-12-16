/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.services;

import abey.entities.Boutique;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author disavinr
 */
@Stateless
public class BoutiqueService extends AbstractService<Boutique> {

    public BoutiqueService() {
        super(Boutique.class);
    }

    public List<Boutique> rechercheBoutiques(String recherche) {
        TypedQuery<Boutique> query = em.createNamedQuery("Boutique.recherche",
                Boutique.class);
        query.setParameter(1, "%" + recherche.replace("%", "\\%") + "%");
        List<Boutique> boutiques = query.getResultList();
        if (boutiques != null && !boutiques.isEmpty()) {
            return boutiques;
        }
        return new ArrayList<>();
    }

}
