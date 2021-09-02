package com.servletjsp.base.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class ConverterUtil {
    /**
     * 
     * @param string
     * @param defaultValue
     * @return
     */
    public static BigDecimal convetStringToBigDecimal(String string, BigDecimal defaultValue) {
        if (StringUtils.isBlank(string)) {
            return defaultValue;
        }

        return new BigDecimal(string);
    }
}
