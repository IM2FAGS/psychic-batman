package abey.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
