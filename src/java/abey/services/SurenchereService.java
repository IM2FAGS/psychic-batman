package abey.services;

import abey.entities.Surenchere;
import abey.entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author nicolas
 */
@Stateless
public class SurenchereService extends AbstractService<Surenchere> {

    public SurenchereService() {
        super(Surenchere.class);
    }

    public List<Surenchere> getSurencheres(Utilisateur utilisateur) {
        TypedQuery<Surenchere> query = em.createNamedQuery("Surenchere.getSurencheres",
                Surenchere.class);
        query.setParameter(1, utilisateur);
        return query.getResultList();
    }

    public List<Surenchere> getSurencheresGagnantes(Utilisateur utilisateur) {
        TypedQuery<Surenchere> query = em.createNamedQuery("Surenchere.getSurencheresGagnantes",
                Surenchere.class);
        query.setParameter(1, utilisateur);
        return query.getResultList();
    }

}
