package abey.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findAllOrderedByColumn(String colName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(entityClass);
        //TODO tentative de tri accent-insensitive
//        cb.function("nlssort", String.class, cq.from(Categorie.class).get("nom"), /*dept_.suppler_name,*/ cb.literal("NLS_SORT=BINARY_AI"));
        cq.select(root).orderBy(cb.asc(root.get(colName)));

        return em.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findInsensitiveLike(String colName, String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(entityClass);
        cq.select(root).where(cb.like(
                cb.lower(root.<String>get(colName)),
                "%" + search.toLowerCase().replace("%", "\\%") + "%"));

        return em.createQuery(cq).getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
