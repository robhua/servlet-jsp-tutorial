package com.servletjsp.base.common.expceitons;

import java.io.Serializable;
import java.util.Arrays;

import com.servletjsp.base.common.constants.CommonMessageConstants;

public class ErrorData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String      errorCode;
    private final String[]    detailMessages;

    public ErrorData(String errorCode) {
        this(errorCode, new String[] { null });
    }

    public ErrorData(String errorCode, String detailMessage) {
        this(errorCode, new String[] { detailMessage });
    }

    /**
     * Constructor with errorCode and detail information objects.
     * 
     * @param erroCode      String
     * @param detailMessage String[]
     */
    public ErrorData(String erroCode, String[] detailMessage) {
        this.errorCode = erroCode;
        if (null == detailMessage) {
            this.detailMessages = new String[] { CommonMessageConstants.NULL_STRING };
            return;
        }
        this.detailMessages = new String[detailMessage.length];
        for (int i = 0; i < detailMessage.length; i++) {
            String _message = (null == detailMessage[i] ? CommonMessageConstants.NULL_STRING : detailMessage[i]);
            this.detailMessages[i] = _message;
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String[] getDetailMessages() {
        return detailMessages;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub RASJP
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(getClass())) {
            ErrorData _tmp = (ErrorData) obj;
            if ((null == errorCode && _tmp.errorCode != null)
                    || errorCode != null && !errorCode.equals(_tmp.errorCode)) {
                return false;
            }
            return Arrays.equals(detailMessages, _tmp.detailMessages);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer _buf = new StringBuffer();
        _buf.append("errorCode:").append(errorCode).append('\n');
        for (int i = 0; i < detailMessages.length; i++) {
            _buf.append("detailMessage[").append(i).append("]").append(detailMessages[i]).append("\n");
        }
        return _buf.toString();
    }

}
