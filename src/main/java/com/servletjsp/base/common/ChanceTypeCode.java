package com.servletjsp.base.common;

public enum ChanceTypeCode {
    ALL("AL"), JAPAN("JP"), KOREA("KR"), TAIWAN("TW"), INDIA("IN"), ASEAN("AS");

    private String chanceTypeCode;

    ChanceTypeCode(String chanceTypeCode) {
        this.chanceTypeCode = chanceTypeCode;
    }

    public String getChanceTypeCode() {
        return chanceTypeCode;
    }

    public void setChanceTypeCode(String chanceTypeCode) {
        this.chanceTypeCode = chanceTypeCode;
    }

}
