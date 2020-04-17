package com.protocol.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PushMsg
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/13
 * @Version V1.0
 **/
@Data
public class PushMsg {
    private String serviceId;
    private String deviceId;
    private Object data;
    private Date date = new Date();
}
