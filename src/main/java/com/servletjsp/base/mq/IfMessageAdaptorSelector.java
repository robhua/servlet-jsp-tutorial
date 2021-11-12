package com.servletjsp.base.mq;

import java.util.Date;

import com.servletjsp.base.common.Operator;

public interface IfMessageAdaptorSelector<T> {
    /**
     * Get adaptor
     * 
     * @param record   Receive TBL
     * @param operator Operator
     * @param statDate Date
     * @return {@link IfMessageAdapter}
     */
    IfMessageAdapter<T> getAdaptor(T record, Operator operator, Date statDate);
}
