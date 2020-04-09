package com.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DeCodePluginDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Data
public class DeCodePluginDO extends BaseModel {
    private String name;
    private String url;
    private String executeClass;
}
