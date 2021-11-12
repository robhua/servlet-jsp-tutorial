package com.servletjsp.base.ejb.jpa;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.slf4j.Logger;

public abstract class AbstractDao<T> {
    private final Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract EntityManager getEntityManager();

    protected abstract Logger getLogger();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public boolean exists(Object id) {
        return find(id) != null;
    }

    public T find(Object id) {
        getLogger().trace("[find] " + entityClass.getName() + " : id = " + id);
        return getEntityManager().find(entityClass, id, LockModeType.OPTIMISTIC);
    }

    private void writeTraceLog(QueryBean queryBean) {
        getLogger().trace(queryBean.getQueryString());
        getLogger().trace(queryBean.getParamString());
    }
}
