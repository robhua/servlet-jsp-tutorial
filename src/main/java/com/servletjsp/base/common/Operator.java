package com.servletjsp.base.common;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.CommonConstants;
import com.servletjsp.base.common.constants.WebCommonConstants;

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

    public Locale obtainLocale() {
        return new Locale(StringUtils.isNoneBlank(getLanguage()) ? getLanguage() : WebCommonConstants.DEFAULT_LANGUAGE);
    }

    private String getLanguage() {
        // TODO Auto-generated method stub
        return null;
    }
}
