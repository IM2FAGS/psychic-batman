package abey.services;

import abey.entities.Enchere;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
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
                getEqualQueryPredicate("enchereGagnee", null, root),
                cb.lessThanOrEqualTo(root.<Date>get("dateFin"), new Date())
        );

        return em.createQuery(cq).getResultList();
    }
}
