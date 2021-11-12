package com.servletjsp.base.common.expceitons;

public class ApplicationSystemException extends AbstractApplicationRuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationSystemException(String code) {
        super(code);
    }

    public ApplicationSystemException(String code, Throwable cause) {
        super(code, cause);
    }

    public ApplicationSystemException(String code, String detailMessage) {
        super(code);
        addErrorCode(code, detailMessage);
        
    }
}
