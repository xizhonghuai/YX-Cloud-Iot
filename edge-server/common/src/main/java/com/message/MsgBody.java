package com.message;

import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName MsgBody
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/25
 * @Version V1.0
 **/
public class MsgBody {

    private String deviceId;
    private Date date;
    private HashMap<String,Object> kpi;

    public MsgBody() {
    }

    public MsgBody(String deviceId, Date date, HashMap<String, Object> kpi) {
        this.deviceId = deviceId;
        this.date = date;
        this.kpi = kpi;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HashMap<String, Object> getKpi() {
        return kpi;
    }

    public void setKpi(HashMap<String, Object> kpi) {
        this.kpi = kpi;
    }

    @Override
    public String toString() {
        return "MsgBody{" +
                "deviceId='" + deviceId + '\'' +
                ", date=" + date +
                ", kpi=" + kpi +
                '}';
    }
}
