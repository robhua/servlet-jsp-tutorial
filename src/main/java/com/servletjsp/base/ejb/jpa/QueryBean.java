package com.servletjsp.base.ejb.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Query Bean
 * 
 * @author Admin
 *
 */
public class QueryBean {
    private final String                       queryString;
    private final List<IfQueryParameterBinder> paramBinders;

    public QueryBean(String queryString, List<IfQueryParameterBinder> parameterBinders) {
        this.queryString = queryString;
        this.paramBinders = parameterBinders;
    }

    /**
     * 
     * @return "[no parameter]" param is null or size 0, or else "[param1, param2, param3]"
     */
    public String getParamString() {
            if (paramBinders == null || paramBinders.size() == 0) {
                return "[no parameter]";
            } else {
                return "[" + StringUtils.join(paramBinders, ",") + "]";
             }
    }
    public String getQueryString() {
        return queryString;
    }

    public List<IfQueryParameterBinder> getParamBinders() {
        return paramBinders;
    }

}
