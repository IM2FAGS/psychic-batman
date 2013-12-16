package abey.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author toinou
 * @param <T>
 */
public abstract class AbstractService<T> {

    @PersistenceContext
    protected EntityManager em;
    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T instance) {
        em.persist(instance);
    }

    public void remove(T instance) {
        em.remove(em.merge(instance));
    }

    public void edit(T instance) {
        em.merge(instance);
    }

    public T find(long id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findAllOrderedByColumn(String colName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        //TODO tentative de tri accent-insensitive
//        cb.function("nlssort", String.class, cq.from(Categorie.class).get("nom"), /*dept_.suppler_name,*/ cb.literal("NLS_SORT=BINARY_AI"));
        cq.select(cq.from(entityClass)).orderBy(cb.asc(cq.from(entityClass).get(colName)));

        return em.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
