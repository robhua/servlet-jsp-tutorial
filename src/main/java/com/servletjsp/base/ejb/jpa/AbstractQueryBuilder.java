package com.servletjsp.base.ejb.jpa;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Admin
 *
 */
public abstract class AbstractQueryBuilder<T> {
    private final StringBuilder  queryBuffer = new StringBuilder();
    List<IfQueryParameterBinder> paramBinders;

    public abstract QueryBean buildCountQuery(String field);

    /**
     * Build query bean
     * 
     * @return {@link QueryBean}
     */
    public QueryBean build() {
        return new QueryBean(queryBuffer.toString(),
                paramBinders == null ? Collections.<IfQueryParameterBinder>emptyList() : paramBinders);
    }

    @SuppressWarnings("unchecked")
    public T append(String sql) {
        this.queryBuffer.append(sql);
        return (T) this;
    }

    protected String getQueryString() {
        return queryBuffer.toString();
    }
}
