package com.servletjsp.tutorial.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.servletjsp.base.common.constants.CommonConstants;
import com.servletjsp.base.common.expceitons.ApplicationLogicException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static com.servletjsp.base.common.constants.SystemErrorCodeConstatns.LOGIC;

public class CrudDateUtil {
    public enum DateFormat {
        DATE_WITHOUT_HOURS, DATE_WITHOUT_HOURS_SLASH, ISO_DATE_FORMAT, SHORT_DATE, DATETIME_FORMAT;

        public String getFormatPattern() {
            if (this == DATE_WITHOUT_HOURS) {
                return "yyyyMMdd";
            } else if (this == DATE_WITHOUT_HOURS_SLASH) {
                return "yyyy/MM/dd";
            } else if (this == ISO_DATE_FORMAT) {
                return "yyyy-MM-dd";
            } else if (this == SHORT_DATE) {
                return "yy/MM/dd";
            } else if (this == DATETIME_FORMAT) {
                return "yyyy/MM/dd HH:mm:ss";
            } else {
                return "yyyy/mm/dd hh:mm:ss a";
            }
        }
    }

    /**
     * Convert a string date to an expected string date pattern
     * 
     * @param dateToConvert           A string of date to convert
     * @param dateToConvertFormat     Format pattern of input string date
     * @param expectDateConvertFormat Format pattern of output string date
     * @return A string of date after convert
     */
    public static String convert(String dateToConvert, DateFormat dateToConvertFormat,
            DateFormat expectDateConvertFormat) {
        Date _date = generateDate(dateToConvert, dateToConvertFormat);
        return format(_date, expectDateConvertFormat);
    }

    /**
     * Format a date for the given format mode
     * 
     * @param date A date
     * @param mode Format pattern
     * @return A string of date after format
     */
    public static String format(Date date, DateFormat mode) {
        String _date = null;
        try {
            if (date != null && mode != null) {
                SimpleDateFormat _format = new SimpleDateFormat(mode.getFormatPattern());
                _date = _format.format(date);
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new ApplicationLogicException(LOGIC, e);
        }
        return _date;
    }

    /**
     * Parse a date from string for the given format mode
     * 
     * @param date A string of date
     * @param mode Format pattern
     * @return A date after parse
     */
    public static Date generateDate(String date, DateFormat mode) {
        Date _date = null;
        try {
            if (isNotBlank(date) && mode != null) {
                SimpleDateFormat _format = new SimpleDateFormat(mode.getFormatPattern());
                _date = _format.parse(date);
            }
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            throw new ApplicationLogicException(LOGIC, e);
        }
        return _date;
    }

    /**
     * Find next working day
     * 
     * @param date The input date
     * @return {@link Date} The next working day
     */
    public static Date findNextWorkingDay(Date date) {
        if (date != null) {
            Calendar _calendar = Calendar.getInstance();
            _calendar.setTime(date);

            while ((_calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                    || (_calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                _calendar.add(Calendar.DATE, CommonConstants.INT_1);
            }
            return _calendar.getTime();
        }
        return null;
    }

    /**
     * Gets the dates between two days.
     * 
     * @param startDate the start date
     * @param endDate   the end date
     * @return the dates between two days
     */
    public static List<Date> getDatesBetweenTwoDays(Date startDate, Date endDate) {
        List<Date> _rangeDates = new ArrayList<Date>();
        Calendar _start = Calendar.getInstance();
        _start.setTime(startDate);
        Calendar _end = Calendar.getInstance();
        _end.setTime(endDate);

        while (_start.before(_end)) {
            Date _date = _start.getTime();
            _rangeDates.add(_date);
            _start.add(Calendar.DATE, CommonConstants.INT_1);
        }
        return _rangeDates;
    }
}
