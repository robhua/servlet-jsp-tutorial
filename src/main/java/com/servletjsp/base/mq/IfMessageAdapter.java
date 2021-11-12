package com.servletjsp.base.mq;

import java.util.Date;

import com.servletjsp.base.common.Operator;
import com.servletjsp.base.common.expceitons.AbstractApplicationRuntimeException;
import com.servletjsp.base.mq.constants.MQReceiveTblStatus;

/**
 * IfMessageAdapter
 * 
 * @author Admin
 *
 */
public interface IfMessageAdapter<T> {
    /**
     * Call manager
     * 
     * @param receiveBean Recevice TBL
     * @param operator    Operator
     * @param startDate   Date
     */
    void callManager(T receiveBean, Operator operator, Date startDate);

    /**
     * Handle error
     * 
     * @param e            Exception
     * @param receivedBean Receive TBL
     * @param operator     Operator
     * @param startDate    Date
     * @return {@link MQReceiveTblStatus}
     */
    MQReceiveTblStatus onError(AbstractApplicationRuntimeException e, T receivedBean, Operator operator,
            Date startDate);
}
