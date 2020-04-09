package com.model;

import com.mapper.DeCodePluginMapper;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName IotNodeDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/8
 * @Version V1.0
 **/

@Data
public class IotNodeDO {

    private Integer id;
    private String serviceId;
    private String serverType;
    private String serverName;
    private Boolean isStatus;
    private Integer idle;
    private Integer ruleEngineId;

    private String ports;
    private String comName;
    private Integer baud;
    private String host;
    private String topic;
    private String userName;
    private String passWord;
    private Date createDate;









}
