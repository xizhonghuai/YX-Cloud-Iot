package com.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName DeviceMsgDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/25
 * @Version V1.0
 **/
public class DeviceMsgDO {

    /*
    CREATE TABLE `device_msg` (
  `id` int(11) NOT NULL,
  `service_id` varchar(64) NOT NULL,
  `device_id` varchar(64) NOT NULL,
  `msgbody` text,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   */

    private Integer id;
    @JSONField(serialize=false)
    private String serviceId;

    @JSONField(serialize=false)
    private String deviceId;

    @JSONField(serialize=false)
    private String msgBody;


    private HashMap<String,Object> messageBody;

    @JSONField(serialize=false)
    private Date createDate;

    public DeviceMsgDO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMsgBody() {

        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
        this.messageBody = JSON.parseObject(msgBody,HashMap.class);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public HashMap<String, Object> getMessageBody() {
        return messageBody;
    }


    @Override
    public String toString() {
        return "DeviceMsgDO{" +
                "id=" + id +
                ", serviceId='" + serviceId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", msgBody=" + msgBody +
                ", createDate=" + createDate +
                '}';
    }
}
