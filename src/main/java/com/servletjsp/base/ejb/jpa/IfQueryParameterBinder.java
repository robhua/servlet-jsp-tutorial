package com.servletjsp.base.ejb.jpa;

import javax.persistence.Query;

public interface IfQueryParameterBinder {

    void bind(Query _query);

}
