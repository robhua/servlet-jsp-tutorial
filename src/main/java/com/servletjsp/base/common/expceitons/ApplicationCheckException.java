package com.servletjsp.base.common.expceitons;

public class ApplicationCheckException extends AbstractApplicationRuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationCheckException(String code, Throwable cause) {
        super(code, cause);
    }

}
