package repository;

import org.hibernate.*;
import java.util.*;
import jakarta.persistence.*;

public abstract class Repository<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public Repository(final Class<T> clazzToSet, SessionFactory sessionFactory)   {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final long id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int from, int count) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(from);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T create(final T entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
