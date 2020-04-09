package com.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DeviceDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Data
public class DeviceDO extends BaseModel {

    private String deviceId;
    private String deviceName;
    private Integer deCodePluginId;

    

}
