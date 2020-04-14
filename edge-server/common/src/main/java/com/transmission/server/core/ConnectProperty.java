package com.transmission.server.core;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ConnectProperty
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
@Data
public class ConnectProperty {

    private String serviceId;
    private String regId;
    private String address;
    private Date activityTime;
    private Date registerTime;
    private String type;
    @JSONField(serialize=false)
    private String authCode;


}
