package com.servletjsp.base.web.common;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author Admin
 *
 */
public class WebProperty implements Serializable {

    private static final long   serialVersionUID = 1L;
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
