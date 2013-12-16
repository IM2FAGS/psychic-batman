package abey.services;

import abey.entities.Categorie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

@Stateless
public class CategorieService extends AbstractService<Categorie> {

	public CategorieService() {
		super(Categorie.class);
	}
	
        public List<Categorie> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        //TODO tentative de tri accent-insensitive
//        cb.function("nlssort", String.class, cq.from(Categorie.class).get("nom"), /*dept_.suppler_name,*/ cb.literal("NLS_SORT=BINARY_AI"));
        cq.select(cq.from(Categorie.class)).orderBy(cb.asc(cq.from(Categorie.class).get("nom")));

        return em.createQuery(cq).getResultList();
    }

}
