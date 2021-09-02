package com.servletjsp.base.common.expceitons;

public class ApplicationLogicException extends AbstractApplicationRuntimeException  {

    private static final long serialVersionUID = 1L;

    public ApplicationLogicException(String code, Throwable cause) {
        super(code, cause);
    }


}
