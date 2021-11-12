package com.servletjsp.base.common;

import java.util.Collections;
import java.util.List;

/**
 * ListValueResult
 * 
 * @author Admin
 *
 */
public class ListValueResult<T> extends ValueResult<List<T>> {

    private static final long serialVersionUID = 1L;

    private int               totalCount;

    /**
     * Default constructor
     */
    public ListValueResult() {
        super(Collections.<T>emptyList());
    }

    /**
     * Constructor
     * 
     * @param value List of value
     */
    public ListValueResult(List<T> value) {
        super(value);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
