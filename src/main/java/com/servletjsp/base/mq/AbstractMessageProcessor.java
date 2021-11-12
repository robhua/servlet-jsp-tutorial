package com.servletjsp.base.mq;

import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;

import com.servletjsp.base.common.Operator;
import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;
import com.servletjsp.base.mq.constants.MQReceiveTblStatus;

public abstract class AbstractMessageProcessor<T> implements IfMessageProcessor {
    @Resource
    protected UserTransaction userTransaction;

    @Inject
    protected Logger          logger;

    @Override
    public void onListenerRetryableMaxCount(TextMessage message, IfMessageSender messageSender) throws JMSException {
        messageSender.send(message);
    }

    @Override
    public void onTriggerMessage(TextMessage message, UserTransaction userTransaction) throws JMSException {
        String _primaryKey = getPrimaryKey(message);
        logger.debug(this.getClass().getName(), "onTriggerMessage",
                "Get MQ message (primary key) : [" + _primaryKey + "]");
        try {
            userTransaction.begin();
            if (message.getJMSRedelivered()) {
                processTriggerRedelivered(_primaryKey);
            } else {
                processTriggerExecute(_primaryKey, userTransaction);
            }
            processCommit(userTransaction);
        } catch (Throwable e) {
            processRollBack(userTransaction);
            if (message.getJMSRedelivered()) {
                logger.info(this.getClass().getName(), "onTriggerMessage",
                        "TRIGGER_MDB_REDELIVERED_ERRO(" + getInterfaceName(message.getText()) + "):" + _primaryKey);
                return;
            } else {
                logger.info(this.getClass().getName(), "onTriggerMessage",
                        "TRIGGER_MDB_ERROR" + getInterfaceName(message.getText()) + "):" + _primaryKey);
                throw new ApplicationSystemException(SystemErrorCodeConstants.ERRO_JMS_ACCESS);
            }
        }
    }

    /**
     * Process roll-back
     * 
     * @param userTransaction
     */
    private void processRollBack(UserTransaction userTransaction) {
        if (userTransaction == null)
            return;
        try {
            userTransaction.rollback();
        } catch (Exception e) {
            // Nop handle exception
        }
    }

    /**
     * Commit transaction
     * 
     * @param userTransaction
     */
    private void processCommit(UserTransaction userTransaction) {
        if (userTransaction == null)
            return;
        try {
            userTransaction.commit();
        } catch (Exception e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        }
    }

    /**
     * Process trigger
     * 
     * @param primaryKey
     * @param userTransaction
     * @return {@link Exception}
     */
    private void processTriggerExecute(String primaryKey, UserTransaction userTransaction) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * Process trigger re-delivered
     * 
     * @param primaryKey
     */
    private void processTriggerRedelivered(final String primaryKey) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEnableListnerProcess() {
        return true;
    }

    /**
     * Get adaptor selector
     * 
     * @param receivedBean Receive TBL
     * @param operator     Operator
     * @return Adapter
     * @throws Exception
     */
    protected abstract IfMessageAdapter<T> getAdapterSelector(T receivedBean, Operator operator) throws Exception;

    /**
     * Get the Primary key
     * 
     * @param message {@link TextMessage}
     * @return String
     * @throws JMSException
     */
    protected abstract String getPrimaryKey(TextMessage message) throws JMSException;

    /**
     * Get the interface name <br>
     * onListenerMessage/onTriggerMessage
     * 
     * @param prirmaryKey
     * @return String
     */
    protected abstract String getInterfaceName(String prirmaryKey);

    /**
     * Find message receive TBL
     * 
     * @param primaryKey The primary key
     * @return <T>
     * @throws Exception
     */
    protected abstract T findMessageReceiveTbl(String primaryKey) throws Exception;

    /**
     * Update status
     * 
     * @param status        {@link MQReceiveTblStatus}
     * @param errorCodeText String
     * @param receivedBean  Receive TBL
     * @param operator      {@link Operator}
     * @param startDate     Date
     */
    protected abstract void updateStatus(MQReceiveTblStatus status, String errorCodeText, T receivedBean,
            Operator operator, Date startDate);

    /**
     * Get chance operator
     * 
     * @param primaryKey
     * @return {@link Operator}
     */
    protected abstract Operator getChanceOperator(String primaryKey);
}
