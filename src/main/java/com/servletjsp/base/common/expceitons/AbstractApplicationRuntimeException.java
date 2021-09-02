package com.servletjsp.base.common.expceitons;

public class AbstractApplicationRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String[] params;

    public AbstractApplicationRuntimeException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String[] getParams() {
        return params;
    }
    
}
