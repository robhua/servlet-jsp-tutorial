package com.servletjsp.base.mq;

import javax.jms.TextMessage;

/**
 * Interface message sender.
 * 
 * @author Admin
 *
 */
public interface IfMessageSender {

    /**
     * Send the message.
     * 
     * @param message Message
     */
    void send(final TextMessage message);

}
