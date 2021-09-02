package com.servletjsp.base.common.expceitons;

public class ApplicationDataException extends AbstractApplicationRuntimeException {

    public ApplicationDataException(String code, Throwable cause) {
        super(code, cause);
    }

    private static final long serialVersionUID = 1L;

}
