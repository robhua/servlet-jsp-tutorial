package com.servletjsp.base.common;

public class ValueResult<T> extends Result {
    private static final long serialVersionUID = 1L;
    private T                 value;

    public ValueResult() {
    }

    public ValueResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
