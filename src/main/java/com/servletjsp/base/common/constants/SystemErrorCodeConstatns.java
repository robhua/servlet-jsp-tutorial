package com.servletjsp.base.common.constants;

public final class SystemErrorCodeConstatns {
    public static final String DATA_NOT_FOUND        = "E00001";
    public static final String DB_INSERT             = "E00002";
    public static final String DB_UPDATE             = "E00003";
    public static final String DB_ACCESS             = "E00004";
    public static final String CHANGED_BY_OTHER_USER = "E00005";
    public static final String DATA_ALREADY_EXISTS   = "E00006";
    public static final String SYSTEM                = "E00007";
    public static final String LOGIC                 = "E00008";

    /** Logic error throw by until */
    public static final String SYSTEM_LOGIC          = "E00023";

    /** SQL */
    public static final String TOO_MANY_SEARCH_RESUT = "E00024";

    /**
     * Failed to read property.
     */
    public static final String READ_PROPERTY         = "E00017";
    /**
     * Failed to load property file
     */
    public static final String LOAD_PROPERTY         = "E00018";
}
