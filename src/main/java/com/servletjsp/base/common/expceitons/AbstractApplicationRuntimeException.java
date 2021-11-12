package com.servletjsp.base.common.expceitons;

import java.util.HashSet;
import java.util.Set;

public class AbstractApplicationRuntimeException extends RuntimeException {

    private static final long    serialVersionUID = 1L;

    private final Set<ErrorData> errDataSet       = new HashSet<ErrorData>();
    private String               code;
//    private String[]          params;

    // ***** constructor ******
    protected AbstractApplicationRuntimeException() {

    }

    protected AbstractApplicationRuntimeException(Throwable t) {
        super(t);
    }

    protected AbstractApplicationRuntimeException(String code) {
        super(code);
        this.code = code;
    }

    protected AbstractApplicationRuntimeException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public void addErrorCode(String errCode) {
        errDataSet.add(new ErrorData(errCode));
    }

    public void addErrorCode(String errCode, String detailMessage) {
        errDataSet.add(new ErrorData(errCode, detailMessage));
    }

    public void addErrorCode(String errCode, String[] detailMessage) {
        errDataSet.add(new ErrorData(errCode, detailMessage));
    }

    public String getCode() {
        return code;
    }

//    public String[] getParams() {
//        return params;
//    }

}
