package abey.services;

import abey.entities.Categorie;
import abey.entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author toinou
 */
@Stateless
public class ProduitService extends AbstractService<Produit> {

    public ProduitService() {
        super(Produit.class);
    }

    public List<Produit> rechercheProduits(String recherche) {
        return findInsensitiveLike("nom", recherche);
    }

    public List<Produit> rechercheProduits(String query, Categorie categorieQuery) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Produit> root = cq.from(Produit.class);
        
        List<Predicate> pred = new ArrayList<>();
        if (categorieQuery != null)
            pred.add(getEqualQueryPredicate("categorie", categorieQuery, root));
        if (query != null)
            pred.add(getLikeQueryPredicate("nom", query, root));
        
        cq.select(root).where(cb.and(pred.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }

    public List<Produit> getCoupsDeCoeur() {
        return findAll();
    }

}
