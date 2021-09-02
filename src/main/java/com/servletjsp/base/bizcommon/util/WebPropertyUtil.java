package com.servletjsp.base.bizcommon.util;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class WebPropertyUtil {
    /**
     * Get Property map
     * @param resource Resource
     * @param language Language
     * @return Map<String, String>
     */
    public Map<String, String> createPropertyMap(String resource, String language) {
        ResourceBundle _bundle;
        if (null == language) {
            _bundle = ResourceBundle.getBundle(resource);
        } else {
            _bundle = ResourceBundle.getBundle(resource, new Locale(language));
        }
        
        Map<String, String> _propertyMap = new TreeMap<>();
        for (String _key : _bundle.keySet()) {
            _propertyMap.put(_key, _bundle.getString(_key));
        }
        return _propertyMap;
    }
}
