package com.servletjsp.base.common.expceitons;

public class ApplicationSystemException extends AbstractApplicationRuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationSystemException(String code, Throwable cause) {
        super(code, cause);
    }

}
