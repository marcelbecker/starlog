package de.anderscore.starlog.persistence;

import javax.persistence.EntityManager;

/**
 * Created by MBecker on 20.01.2017.
 */
abstract class AbstractRepository<T> {

    protected EntityManager entityManager;

    protected AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    abstract T get(String identifier);

}
