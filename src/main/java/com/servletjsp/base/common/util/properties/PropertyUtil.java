package com.servletjsp.base.common.util.properties;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;

public class PropertyUtil {
    public static final String   DOT_SEPARATOR = ".";
    private final ResourceBundle bundle;
    private final String         propertyName;

    public PropertyUtil(final String propertyName) {
        try {
            this.propertyName = propertyName;
            bundle = ResourceBundle.getBundle(propertyName);
        } catch (Exception e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.ERROR_LOAD_PROPERTY, propertyName);
        }
    }

    public PropertyUtil(final String propertyName, final String languageCode) {
        try {
            this.propertyName = propertyName;
            if (languageCode == null || languageCode.matches("\\s*")) {
                bundle = ResourceBundle.getBundle(propertyName);
                return;
            }
            bundle = ResourceBundle.getBundle(propertyName, new Locale(languageCode));
        } catch (Exception e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.ERROR_LOAD_PROPERTY, propertyName);
        }
    }

    /**
     * Read property.
     * 
     * @param key         property key
     * @param defaultName default name for return.
     * @return if exists property value then property value else defaultName.
     */
    public String readProperty(final String key, final String defaultName) {
        if (bundle == null) {
            return defaultName;
        }
        try {
            final String _readName = bundle.getString(key);
            if (_readName == null || _readName.length() == 0) {
                return defaultName;
            }
            return _readName;
        } catch (RuntimeException e) {
            return defaultName;
        }
    }

    /**
     * Read property<br>
     * if property is not exists, throw exception.
     * 
     * @param key property key
     * @throws ApplicationSystemException - if key or property is not exists.
     */
    public String reaProperty(final String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.ERROR_READ_PROPERTY, key);
        }
    }

    /**
     * read property<br>
     * Key is concat prefix, SEPARATOR, key.
     * @param prefix key first prefix
     * @param key key second prefix
     * @param defaultName default name for return
     * @return if exist property value then property value else defaultName
     */
    public String readProperty(
            final String prefix, 
            final String key, 
            final String defaultName) {
        return readProperty(prefix + DOT_SEPARATOR + key, defaultName);
    }
    /**
     * read property<br>
     * Key is concat prefix1, SEPARATOR, prefix2, key.
     * @param prefix1 key first prefix
     * @param prefix2 key second prefix
     * @param key property key
     * @param defaultName default name for return
     * @return if exist property value then property value else defaultName
     */
    public String readProperty(
            final String prefix1,
            final String prefix2,
            final String key, 
            final String defaultName) {
        return readProperty(prefix1 + DOT_SEPARATOR + prefix2 + key, defaultName);
    }

    public String getPropertyName() {
        return propertyName;
    }

}
