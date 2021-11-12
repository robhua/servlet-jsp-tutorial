package com.servletjsp.base.ejb.jpa;

import java.util.Collections;

public class NativeSQLBuilder extends AbstractQueryBuilder<NativeSQLBuilder> {

    @Override
    public QueryBean buildCountQuery(String field) {
        String _s = "SELECT COUNT (" + field + ") FROM (" + getQueryString() + ") ORG";
        return new QueryBean(_s, paramBinders == null ? Collections.<IfQueryParameterBinder>emptyList() : paramBinders);
    }

}
