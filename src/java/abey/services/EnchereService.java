package abey.services;

import abey.entities.Enchere;
import abey.entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author disavinr
 */
@Stateless
public class EnchereService extends AbstractService<Enchere> {

    public EnchereService() {
        super(Enchere.class);
    }

    public List<Enchere> getEncheresAClore() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Enchere> root = cq.from(Enchere.class);

        cq.select(root).where(
                getEqualQueryPredicate("terminee", false, root),
                cb.lessThanOrEqualTo(root.<Date>get("dateFin"), new Date())
        );

        return em.createQuery(cq).getResultList();
    }

    public List<Enchere> getEncheresCreeesEnCours(Utilisateur utilisateur) {
        TypedQuery<Enchere> query = em.createNamedQuery("Enchere.getEncheresCreeesEnCours", Enchere.class);
        query.setParameter(1, utilisateur);
        return query.getResultList();
    }

    public List<Enchere> getEncheresCreeesTerminees(Utilisateur utilisateur) {
        TypedQuery<Enchere> query = em.createNamedQuery("Enchere.getEncheresCreeesTerminees", Enchere.class);
        query.setParameter(1, utilisateur);
        return query.getResultList();
    }

}
