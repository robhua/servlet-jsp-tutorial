package com.servletjsp.base.ejb.jpa;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;

@Named
@Dependent
public class CommonDao {

    @PersistenceContext(unitName = "chance_PU_READ")
    private EntityManager em;

    @Inject
    private Logger        logger;

    /**
     * Return numbers of rows in query results
     * 
     * @param queryBean {@link QueryBean}
     * @return Number of rows
     */
    public int selectIntNativeQuery(QueryBean queryBean) {
        writeTraceLog(queryBean);
        Query _query = em.createNativeQuery(queryBean.getQueryString());
        for (IfQueryParameterBinder _binder : queryBean.getParamBinders()) {
            _binder.bind(_query);
        }
        return ((Number) _query.getSingleResult()).intValue();
    }

    /**
     * Native Query execute
     * 
     * @param queryBean {@link QueryBean}
     * @return List<?>
     */
    public List<?> findByNativeQuery(QueryBean queryBean) {
        writeTraceLog(queryBean);
        Query _query = em.createNativeQuery(queryBean.getQueryString());
        for (IfQueryParameterBinder _binder : queryBean.getParamBinders()) {
            _binder.bind(_query);

        }

        return _query.getResultList();
    }

    /**
     * Find result by range
     * 
     * @param queryBean  {@link QueryBean}
     * @param firstResut The first result
     * @param maxResult  The max result
     * @return
     */
    public List<?> findByNativeQueryRange(QueryBean queryBean, int firstResut, int maxResult) {
        writeTraceLog(queryBean);
        Query _query = em.createNamedQuery(queryBean.getQueryString());
        for (IfQueryParameterBinder _binder : queryBean.getParamBinders()) {
            _binder.bind(_query);
        }
        _query.setFirstResult(firstResut);
        _query.setMaxResults(maxResult);
        return _query.getResultList();
    }

    /**
     * Write trace log for query
     * 
     * @param queryBean {@link QueryBean}
     */
    private void writeTraceLog(QueryBean queryBean) {
        logger.trace(queryBean.getQueryString());
        logger.trace(queryBean.getParamString());
    }
}
