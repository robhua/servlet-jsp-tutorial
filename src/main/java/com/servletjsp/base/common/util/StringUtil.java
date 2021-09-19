package com.servletjsp.base.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.servletjsp.base.common.constants.CommonConstants;

public class StringUtil {
    private static volatile String spaces = " ";

    public static final String     QTY_0  = "0";

    /**
     * fillZero<br>
     * add 0 to head of inputted string
     * 
     * @param orgString
     * @param figure
     * @return
     */
    public static String fillZero(String orgString, int figure) {
        StringBuffer _buffer = new StringBuffer(orgString);
        for (int i = orgString.length(); i < figure; i++) {
            _buffer.insert(0, "0");
        }
        return _buffer.toString();
    }

    /**
     * Create 3 digit string of input value.
     * 
     * @param longValue long
     * @return seq string
     */
    public static String createSeqString(long longValue) {
        String _longValueString = String.valueOf(longValue);
        if (_longValueString.length() >= 3) {
            _longValueString = _longValueString.substring(_longValueString.length() - 3);
        } else {
            _longValueString = fillZero(_longValueString, 3);
        }
        return _longValueString.toString();
    }

    /**
     * Fill space back to origin string
     * 
     * @param orgString String
     * @param fingure   int
     * @return filled string
     */
    public static String fillSpaceBack(String orgString, int fingure) {
        StringBuffer _buffer = new StringBuffer(orgString);
        for (int i = orgString.length(); i < fingure; i++) {
            _buffer.append(" ");
        }
        return _buffer.toString();
    }

    public static String quickFillSpaceBack(final String orgString, final int figure) {
        final int _need = figure - orgString.length();
        if (_need <= 0) {
            return orgString;
        }

        String _spaces = spaces;// support thread safe
        if (_spaces.length() < _need) {
            final StringBuffer _buffer = new StringBuffer(_spaces);
            while (_buffer.length() < _need) {
                _buffer.append(_buffer);
            }
            _spaces = _buffer.toString();
            spaces = _spaces;
        }
        return (orgString + _spaces).substring(0, figure);
    }

    public static String formatStringNumber(Object value) {
        if (null == value) {
            return CommonConstants.EMPTY;
        } else {
            String _result;
            BigDecimal _quantity;
            if (value instanceof Long) {
                _quantity = BigDecimal.valueOf((Long) value);
            } else if (value instanceof Double) {
                _quantity = BigDecimal.valueOf((Double) value);
            } else if (value instanceof Integer) {
                _quantity = BigDecimal.valueOf((Integer) value);
            } else if (value instanceof String) {
                if (StringUtils.isBlank((String) value)) {
                    _quantity = null;
                } else {
                    _quantity = BigDecimal.valueOf(Double.valueOf((String) value));
                }
            } else if (!(value instanceof BigDecimal)) {
                throw new NumberFormatException("Convert quantity error");
            } else {
                _quantity = (BigDecimal) value;
            }

            try {
                if (null == _quantity) {
                    _result = CommonConstants.EMPTY;
                } else {
                    if (_quantity.compareTo(BigDecimal.ZERO) > CommonConstants.INT_0) {
                        _result = String.format("%,.0f",
                                _quantity.setScale(CommonConstants.INT_0, BigDecimal.ROUND_DOWN));
                    } else {
                        _result = QTY_0;
                    }
                }
                return _result;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Convert quantity error");
            }

        }
    }

    /**
     * trim spaces on right hand. ex. rtrimString("xxx "); //reutrn "xxx"
     * rtrimString(" yyy "); //reutrn " yyy" rtrimString(" z\t \n "); //reutrn " z"
     * 
     * @param orgString String
     * @return if orgString == null then null, else sub-string of the orgString.
     */
    public static String rtrimString(String orgString) {
        if (orgString == null) {
            return null;
        }

        char[] _c = orgString.toCharArray();

        int i = _c.length - CommonConstants.INT_1;
        for (; i >= 0; i--) {
            if (_c[i] > ' ')
                break;
        }

        if (i == _c.length - CommonConstants.INT_1) {
            return orgString;
        } else if (i == -1) {
            return CommonConstants.EMPTY;
        } else {
            return orgString.substring(0, i + 1);
        }
    }

    public static void main(String[] args) {
//        Object value = null;
//        Object value = new Long(234);
//        Object value = "234.46";
//        Object value = "234.9";
//        Object value = "0.9";
        String value = "     3      ";
        System.out.println(rtrimString(value));
    }
}
