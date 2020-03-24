package com.transmission.server.core;

import java.util.Date;

/**
 * @ClassName Connect
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/

public class Connect {

    private String regId;
    private String authorizedCode;
    private String address;
    private Date activityTime;
    private Date registerTime;

    public Connect() {
    }

    public Connect(String regId, String authorizedCode, String address, Date activityTime, Date registerTime) {
        this.regId = regId;
        this.authorizedCode = authorizedCode;
        this.address = address;
        this.activityTime = activityTime;
        this.registerTime = registerTime;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAuthorizedCode() {
        return authorizedCode;
    }

    public void setAuthorizedCode(String authorizedCode) {
        this.authorizedCode = authorizedCode;
    }

    @Override
    public String toString() {
        return "Connect{" +
                "regId='" + regId + '\'' +
                ", authorizedCode='" + authorizedCode + '\'' +
                ", address='" + address + '\'' +
                ", activityTime=" + activityTime +
                ", registerTime=" + registerTime +
                '}';
    }
}
