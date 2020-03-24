package com.transmission.server.debug;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName DebugMsg
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class DebugMsg {

    public DebugMsg(String serviceId, String regId, Object data) {
        this.serviceId = serviceId;
        this.regId = regId;
        this.data = data;
    }

    public DebugMsg() {
    }

    private String serviceId;
    private String regId;
    private Object data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DebugMsg{" +
                "serviceId='" + serviceId + '\'' +
                ", regId='" + regId + '\'' +
                ", data=" + data +
                '}';
    }
}
