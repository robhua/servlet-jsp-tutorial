package com.servletjsp.base.mq;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.transaction.UserTransaction;

public interface IfMessageProcessor {
    boolean isEnableListnerProcess();

    void onListenerMessage(TextMessage message, IfMessageSender messageSender) throws JMSException;

    void onListenerRetryableMaxCount(TextMessage message, IfMessageSender messageSender) throws JMSException;

    void onTriggerMessage(TextMessage message, UserTransaction userTransaction) throws JMSException;
}
