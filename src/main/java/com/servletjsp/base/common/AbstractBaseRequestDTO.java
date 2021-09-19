package com.servletjsp.base.common;

import java.io.Serializable;
import java.util.Date;

import com.servletjsp.base.common.constants.CommonConstants;
import com.servletjsp.base.common.util.ProcessingTimeUtil;

/**
 * AbstractBaseRequestDTO
 * 
 * @author Admin
 *
 */
public abstract class AbstractBaseRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private OperatorBean      operatorBean;
    private Date              managerTime;

    private String            actionName;
    private String            actionMuCode;

    public AbstractBaseRequestDTO(Operator operator) {
        operatorBean = operator.getOperatorBean();
        managerTime = ProcessingTimeUtil.getProcessingTime(operator.getTimeZone());
    }

    public AbstractBaseRequestDTO(Operator operator, Date managerTime) {
        this.operatorBean = operator.getOperatorBean();
        this.managerTime = managerTime;
    }

    public AbstractBaseRequestDTO(AbstractBaseRequestDTO requestDTO) {
        operatorBean = requestDTO.getOperatorBean();
        managerTime = requestDTO.getManagerTime();
    }

    public String getTimeZone() {
        return operatorBean == null ? CommonConstants.EMPTY : operatorBean.getTimeZone();
    }

    public String getChanceTypeCode() {
        return operatorBean == null ? CommonConstants.EMPTY : operatorBean.getChanceTypeCode();
    }

    public OperatorBean getOperatorBean() {
        return operatorBean;
    }

    public void setOperatorBean(OperatorBean operatorBean) {
        this.operatorBean = operatorBean;
    }

    public Date getManagerTime() {
        return managerTime;
    }

    public void setManagerTime(Date managerTime) {
        this.managerTime = managerTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionMuCode() {
        return actionMuCode;
    }

    public void setActionMuCode(String actionMuCode) {
        this.actionMuCode = actionMuCode;
    }
}
