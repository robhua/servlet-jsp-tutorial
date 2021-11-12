package com.servletjsp.base.batch;

import java.util.Date;
import java.util.ResourceBundle;

import com.servletjsp.base.batch.constants.BatchCommonConstants;
import com.servletjsp.base.batch.constants.BatchCommonErrorCodeConstants;
import com.servletjsp.base.common.Operator;
import com.servletjsp.base.common.OperatorBean;
import com.servletjsp.base.common.expceitons.ApplicationLogicException;

/**
 * 
 * @author Admin
 *
 */
public abstract class AbstractFlowManager {
    /* Batch id */
    private String     batchId;

    /* Arguments (not inclue batch id) */
    private String[]   mainArgs    = null;

    /* System date time */
    private final Date managerTime = new Date();

    /* System operator */
    private Operator   operator    = null;

    /**
     * Initialize Operator by batchId.
     * 
     * @return initialized Operator
     */

    public AbstractFlowManager() {
        // nop
    }

    /**
     * Initialize operator by batch id
     * 
     * @return Initialized Operator
     */
    private final Operator initializeOperator() {
        if (getBatchId() == null) {
            throw new ApplicationLogicException(BatchCommonErrorCodeConstants.ERROR_BATCH_ID_NOT_FOUND);
        }

        /* Read common properties */
        ResourceBundle _src = ResourceBundle.getBundle(BatchCommonConstants.COM_PROP_PATH);
        if (_src == null) {
            throw new ApplicationLogicException(BatchCommonErrorCodeConstants.ERROR_COM_PROP_NOT_FOUND);
        }

        // create operator
        return createBatchOperator(_src.getString(BatchCommonConstants.USER_KEY));
    }

    /**
     * Create batch operator
     * 
     * @param batchUserId
     * @return {@link Operator}
     */
    private Operator createBatchOperator(String batchUserId) {
        ResourceBundle _src = ResourceBundle.getBundle(BatchCommonConstants.COM_PROP_PATH);
        Operator _operator = new Operator();
        OperatorBean _operatorBean = new OperatorBean(batchUserId, batchUserId);
        _operatorBean.setMuCode(_src.getString(BatchCommonConstants.MU_KEY));
        _operatorBean.setChanceTypeCode(_src.getString(BatchCommonConstants.CHANCE_TYPE_CODE_KEY + getBatchId()));
        _operatorBean.setTimeZone(_src.getString(BatchCommonConstants.TIME_ZONE_KEY + getBatchId()));
        _operator.setOperatorBean(_operatorBean);
        return _operator;
    }

    /**
     * Execute any business logic.<br>
     * sub-class must implement this method for specific use.
     * 
     * @return code.
     * @throws Exception in execute();
     */
    public abstract int execute() throws Exception;

    /**
     * Set arguments and initialize Operator.<br>
     * arguments set this method do not include batchId.
     * 
     * @param strings arguments
     */
    public void setMainArgs(String[] strings) {
        String[] _str = new String[strings.length - 1];

        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                batchId = strings[i];
            } else {
                _str[i - 1] = strings[i];
            }
        }

        mainArgs = _str;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String[] getMainArgs() {
        return mainArgs;
    }

    public Operator getOperator() {
        if (operator == null) {
            operator = initializeOperator();
        }
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Date getManagerTime() {
        return managerTime;
    }

}
