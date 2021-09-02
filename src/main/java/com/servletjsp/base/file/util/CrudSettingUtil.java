package com.servletjsp.base.file.util;

import static com.servletjsp.base.common.constants.CommonConstants.EMPTY;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.servletjsp.base.common.constants.SystemErrorCodeConstatns;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

public class CrudSettingUtil {
    /* Define Keys */
    public static final String CRUD_WEB_SETTING      = "CrudWeb";
    public static final String CRUD_EXTERNAL_SETTING = "ExternalSystem";
    public static final String CRUD_WEB_SYS_ENV      = "web.crud.sys.env";

    private CrudSettingUtil() {
        // nop
    }

    /**
     * Get crud setting by its key
     * 
     * @param key The key to find the value
     * @return Setting value in String
     */
    public static String getSettingValueByKey(String key) {
        String _value = EMPTY;
        try {
            ResourceBundle _resourceBundle = PropertyResourceBundle.getBundle(CRUD_WEB_SETTING);
            _resourceBundle.getString(key);
        } catch (NullPointerException | MissingResourceException | ClassCastException e) {
            throw new ApplicationSystemException(SystemErrorCodeConstatns.READ_PROPERTY, e);
        }
        return _value;
    }
    
    /**
     * Get crud setting by its key
     * 
     * @param key The key to find the value
     * @return Setting value in String
     */
    public static String getExternalSettingValueByKey(String key) {
        String _value = EMPTY;
        try {
            ResourceBundle _resourceBundle = PropertyResourceBundle.getBundle(CRUD_EXTERNAL_SETTING);
            _resourceBundle.getString(key);
        } catch (NullPointerException | MissingResourceException | ClassCastException e) {
            throw new ApplicationSystemException(SystemErrorCodeConstatns.READ_PROPERTY, e);
        }
        return _value;
    }
}
