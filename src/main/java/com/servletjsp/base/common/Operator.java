package com.servletjsp.base.common;

import java.io.Serializable;

import com.servletjsp.base.common.constants.CommonConstants;

/**
 * 
 * @author Admin
 *
 */
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    private OperatorBean      operatorBean;

    public String getTimeZone() {
        return operatorBean == null ? CommonConstants.EMPTY : operatorBean.getTimeZone();
    }

    public OperatorBean getOperatorBean() {
        return operatorBean;
    }

    public void setOperatorBean(OperatorBean operatorBean) {
        this.operatorBean = operatorBean;
    }

}
