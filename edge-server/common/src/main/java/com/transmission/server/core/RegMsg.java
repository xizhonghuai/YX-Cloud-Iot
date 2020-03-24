package com.transmission.server.core;

/**
 * @ClassName RegMsg
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class RegMsg {
    private String regId;
    private String cloudUser;
    private String authCode;

    public RegMsg(String regId, String cloudUser, String authCode) {
        this.regId = regId;
        this.cloudUser = cloudUser;
        this.authCode = authCode;
    }

    public RegMsg() {
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getCloudUser() {
        return cloudUser;
    }

    public void setCloudUser(String cloudUser) {
        this.cloudUser = cloudUser;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "RegMsg{" +
                "regId='" + regId + '\'' +
                ", cloudUser='" + cloudUser + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
