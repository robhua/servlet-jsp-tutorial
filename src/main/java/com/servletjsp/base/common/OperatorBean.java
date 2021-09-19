package com.servletjsp.base.common;

import java.io.Serializable;

/**
 * OperatorBean
 * 
 * @author Admin
 *
 */
public class OperatorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String      userId;
    private final String      userDname;
    private String            userEnName;
    private String            pkiUserId;
    private String            pkiUserPassword;

    private String            timeZone;
    private String            language;

    private String            branchCode;
    private String            custCode;
    private String            dispatchCode;
    private String            dispatchGroupCode;
    private String            chanceTypeCode;

    private String            userMailAddress;
    private String            passwordAdminFlag;
    private String            groupHeadMailAddress;
    private String            groupHeadMailSendFlag;
    private String            salesStaffMailAddress;
    private String            salesStaffMailSendFlag;
    private String            assistantMailAddress;
    private String            assistantMailSendFlag;

    /**
     * Operator bean constructor with user id and its name
     * 
     * @param userId    User ID
     * @param userDName User name
     */
    public OperatorBean(String userId, String userDName) {
        this.userId = userId;
        this.userDname = userDName;
    }

    @Override
    public String toString() {
        return "{\"className\":\"" + this.getClass().getSimpleName() + "\", \"parameters\":{\"userId\":\"" + userId
                + "," + "" + " |} ";
    }

    public String getUserEnName() {
        return userEnName;
    }

    public void setUserEnName(String userEnName) {
        this.userEnName = userEnName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getDispatchCode() {
        return dispatchCode;
    }

    public void setDispatchCode(String dispatchCode) {
        this.dispatchCode = dispatchCode;
    }

    public String getDispatchGroupCode() {
        return dispatchGroupCode;
    }

    public void setDispatchGroupCode(String dispatchGroupCode) {
        this.dispatchGroupCode = dispatchGroupCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserDname() {
        return userDname;
    }

    public String getPkiUserId() {
        return pkiUserId;
    }

    public void setPkiUserId(String pkiUserId) {
        this.pkiUserId = pkiUserId;
    }

    public String getPkiUserPassword() {
        return pkiUserPassword;
    }

    public void setPkiUserPassword(String pkiUserPassword) {
        this.pkiUserPassword = pkiUserPassword;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getPasswordAdminFlag() {
        return passwordAdminFlag;
    }

    public void setPasswordAdminFlag(String passwordAdminFlag) {
        this.passwordAdminFlag = passwordAdminFlag;
    }

    public String getGroupHeadMailAddress() {
        return groupHeadMailAddress;
    }

    public void setGroupHeadMailAddress(String groupHeadMailAddress) {
        this.groupHeadMailAddress = groupHeadMailAddress;
    }

    public String getGroupHeadMailSendFlag() {
        return groupHeadMailSendFlag;
    }

    public void setGroupHeadMailSendFlag(String groupHeadMailSendFlag) {
        this.groupHeadMailSendFlag = groupHeadMailSendFlag;
    }

    public String getSalesStaffMailAddress() {
        return salesStaffMailAddress;
    }

    public void setSalesStaffMailAddress(String salesStaffMailAddress) {
        this.salesStaffMailAddress = salesStaffMailAddress;
    }

    public String getSalesStaffMailSendFlag() {
        return salesStaffMailSendFlag;
    }

    public void setSalesStaffMailSendFlag(String salesStaffMailSendFlag) {
        this.salesStaffMailSendFlag = salesStaffMailSendFlag;
    }

    public String getAssistantMailAddress() {
        return assistantMailAddress;
    }

    public void setAssistantMailAddress(String assistantMailAddress) {
        this.assistantMailAddress = assistantMailAddress;
    }

    public String getAssistantMailSendFlag() {
        return assistantMailSendFlag;
    }

    public void setAssistantMailSendFlag(String assistantMailSendFlag) {
        this.assistantMailSendFlag = assistantMailSendFlag;
    }

    public String getChanceTypeCode() {
        return chanceTypeCode;
    }

    public void setChanceTypeCode(String chanceTypeCode) {
        this.chanceTypeCode = chanceTypeCode;
    }

}
