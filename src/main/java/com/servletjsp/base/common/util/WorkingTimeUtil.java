package com.servletjsp.base.common.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.CommonConstants;
import com.servletjsp.tutorial.until.CrudDateUtil;
import com.servletjsp.tutorial.until.CrudDateUtil.DateFormat;

public class WorkingTimeUtil {
    private static final String BUSINESS_TIME_16H30 = "16:30:00";
    private static final String END_OF_DAY          = "23:59:59";

    /**
     * 
     * @param processingTime The processing time
     * @return true, if is processing time before 16h30
     */
    public static boolean isProcessingTimeBefore16h30(Date processingTime) {
        if (processingTime == null) {
            throw new IllegalArgumentException("Inputt date invalidate");
        }
        Date date16h30 = createDateWithPredefinedTime(processingTime, BUSINESS_TIME_16H30);
        return processingTime.before(date16h30);
    }

    /**
     * Create date by predefined time
     * 
     * @param date The date to get day
     * @param time The predefined time
     * @return Date The created date
     */
    public static Date createDateWithPredefinedTime(Date date, String time) {
        if (date == null || StringUtils.isBlank(time)) {
            throw new IllegalArgumentException("Could not create date without empty inputs");
        }

        Date _date = null;
        String _day = CrudDateUtil.format(_date, DateFormat.DATE_WITHOUT_HOURS_SLASH);
        _date = CrudDateUtil.generateDate(_day + CommonConstants.EMPTY + time, DateFormat.DATETIME_FORMAT);
        return _date;
    }

    /**
     * Check normal (form Monday to Friday) working day
     * 
     * @param date The input date
     * @return boolean True mean normal working day, otherwise false
     */
    public static boolean isNormalWorkingDay(Date date) {
        int _dow = getDayOfWeek(date);
        return (_dow >= Calendar.MONDAY && _dow <= Calendar.FRIDAY);
    }

    /**
     * Get day of week;
     * 
     * @param date The input date
     * @return int The day of week returned
     */
    private static int getDayOfWeek(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Could not to ge day of week without day value");
        }

        Calendar _calendar = Calendar.getInstance();
        _calendar.setTime(date);
        return _calendar.get(Calendar.DAY_OF_WEEK);
    }

}
