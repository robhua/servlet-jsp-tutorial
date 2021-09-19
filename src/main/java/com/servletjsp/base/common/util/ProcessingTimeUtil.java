package com.servletjsp.base.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.servletjsp.base.common.constants.SystemErrorCodeConstatns;
import com.servletjsp.base.common.expceitons.ApplicationDataException;
import com.servletjsp.tutorial.until.CrudDateUtil;
import static com.servletjsp.tutorial.until.CrudDateUtil.DateFormat.DATE_WITHOUT_HOURS;
import static com.servletjsp.base.common.constants.CommonConstants.INT_1;
import static com.servletjsp.base.common.constants.CommonConstants.INT_MINUS_1;

public class ProcessingTimeUtil {
    private static final String   PROPERTY_NAME            = "System";
    private static final String   PROPERTY_SYSTEM_TIMEZONE = "System.timezone";

    private static final String   DATE_FORMAT              = "yy-MM-dd HH:mm:ss.SSS";

    private static ResourceBundle BUNDLE                   = ResourceBundle.getBundle(PROPERTY_NAME);

    /**
     * Default private constructor
     */
    private ProcessingTimeUtil() {
        // nop
    }

    /**
     * Processing time by time-zone
     * 
     * @param targetTimeZone The target time-zone
     * @return Date
     */
    public static Date getProcessingTime(String targetTimeZone) {
        if (StringUtils.isBlank(targetTimeZone)) {
            return new Date();
        }

        try {
            String _systemTimeZone = getPropValue(PROPERTY_SYSTEM_TIMEZONE);
            SimpleDateFormat _formmater = new SimpleDateFormat(DATE_FORMAT);
            Date _processingTime = new Date();
            if (!_systemTimeZone.equals(targetTimeZone)) {
                SimpleDateFormat _sdf = new SimpleDateFormat(DATE_FORMAT);
                TimeZone _targetTimeZone = TimeZone.getTimeZone(targetTimeZone);
                _sdf.setTimeZone(_targetTimeZone);
                String _processingTimeInStr = _sdf.format(_processingTime);

                _processingTime = _formmater.parse(_processingTimeInStr);
            }
            return _processingTime;
        } catch (ParseException e) {
            throw new ApplicationDataException(SystemErrorCodeConstatns.SYSTEM_LOGIC, e);
        }
    }

    /**
     * Get start of processing time
     * 
     * @param targetTimeZone The Target Timezone
     * @return Date
     */
    public static Date getStartOfProcessingTime(String targetTimeZone) {
        Date _processingTime = getProcessingTime(targetTimeZone);
        String _dateWithoutHours = CrudDateUtil.format(_processingTime, DATE_WITHOUT_HOURS);
        return CrudDateUtil.generateDate(_dateWithoutHours, DATE_WITHOUT_HOURS);
    }

    /**
     * Get end processing time;
     * 
     * @param targetTimeZone The target time-zone
     * @return Date
     */
    public static Date getEndOfProcessingTime(String targetTimeZone) {
        Date _startProcessingTime = getStartOfProcessingTime(targetTimeZone);
        Date _startProcessingTimeNextDay = DateUtils.addDays(_startProcessingTime, INT_1);
        return DateUtils.addSeconds(_startProcessingTimeNextDay, INT_MINUS_1);
    }

    /**
     * Get property value
     * 
     * @param key The key
     * @return value of properties key
     */
    private static String getPropValue(String key) {
        try {
            return BUNDLE.getString(key);
        } catch (Exception e) {
            throw new ApplicationDataException(SystemErrorCodeConstatns.ERROR_LOAD_PROPERTY, e);
        }
    }
}
