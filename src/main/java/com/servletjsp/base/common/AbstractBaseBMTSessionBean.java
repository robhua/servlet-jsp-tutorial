package com.servletjsp.base.common;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;

import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.AbstractApplicationRuntimeException;
import com.servletjsp.base.common.expceitons.ApplicationLogicException;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

public abstract class AbstractBaseBMTSessionBean {
    @Inject
    protected UserTransaction userTransaction;

    @Inject
    protected Logger          logger;

    /**
     * Begin transaction in BMT.<br>
     * Helper method for sub-class.
     * 
     */
    protected void beginTransaction() {
        beginTransactionWithTimeout(0);// use container default transaction-timeout value
    }

    /**
     * Begin transaction with TransactionTimeout value in BMT.<br>
     * Helper method for sub-class.
     * 
     * @param timeoutSeconds Transaction timeout value (seconds)
     */
    protected void beginTransactionWithTimeout(int timeoutSeconds) {
        try {
            userTransaction.setTransactionTimeout(timeoutSeconds);
            userTransaction.begin();
        } catch (Exception e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        }
    }

    /**
     * Commit or Roll back transaction.<br>
     * If transaction status is Status.STATUS_ACTIVE then commit the transaction,
     * else roll back the transaction.<br>
     * 
     * Helper method for sub-class.
     * 
     * @param occurredException occurred exception.
     */
    protected void endTransaction(boolean occurredException) {
        try {
            if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    userTransaction.commit();
                } catch (Exception e) {
                    doRollback(userTransaction, "endTransaction");
                    throw e;
                }
            } else if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) {
                doRollback(userTransaction, "endTransaction");
                if (!occurredException) {
                    throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM);
                }
            }
        } catch (AbstractApplicationRuntimeException e) {
            logger.error(getClass().getName(), "endTransaction", e);
            throw e;
        } catch (Exception e) {
            logger.error(getClass().getName(), "endTransaction", e);
            throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        }
    }

    /**
     * Quietly roll back
     * 
     * @param userTransaction
     * @param callMethod
     */
    private void doRollback(UserTransaction userTransaction, String callMethod) {
        try {
            userTransaction.setRollbackOnly();
        } catch (Exception e) {
            logger.error(getClass().getName(), callMethod, e);
        }
    }

    /**
     * 
     * @param e          Throwable
     * @param methodName String
     * @param userID     String
     * @return AbstractApplicationRuntimeException
     */
    protected AbstractApplicationRuntimeException handleException(Throwable e, String methodName, String userID) {
        // Roll back transaction.
        try {
            userTransaction.setRollbackOnly();
        } catch (IllegalStateException | SystemException e1) {
            // IllegalStateException caused in BMT bean.
            // Force roll back
            try {
                userTransaction.rollback();
            } catch (Exception e2) {
                // Not catch roll back exception.
            }
        }

        // Repackage exception.
        if (null == e) {
            return new ApplicationLogicException(SystemErrorCodeConstants.LOGIC);
        } else if (e instanceof AbstractApplicationRuntimeException) {
            return (AbstractApplicationRuntimeException) e;
        } else if (e instanceof Error) {
            // This exception is not catched in business logic.
            logger.error(getClass().getName(), methodName, "uncatch error", userID);

            // wrap exception by ApplicationLogicException and ApplicationFacadeExeption,
            // then throw.
            return new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        } else {
            // This exception is not catched in business logic.
            logger.error(getClass().getName(), methodName, "uncatch exception", userID);

            return new ApplicationLogicException(SystemErrorCodeConstants.LOGIC, e);

        }
    }
}
