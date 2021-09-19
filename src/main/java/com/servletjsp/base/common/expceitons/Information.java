package com.servletjsp.base.common.expceitons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Information implements Serializable {

    private static final long  serialVersionUID = 1L;

    private final String       key;

    private final List<Object> parameters       = new ArrayList<>();

    public Information(String key) {
        this.key = key;
    }

    public Information(String key, Object... parameters) {
        this.key = key;
        this.parameters.addAll(Arrays.asList(parameters));
    }

    public String getKey() {
        return key;
    }

    public List<Object> getParameters() {
        return parameters;
    }

}
