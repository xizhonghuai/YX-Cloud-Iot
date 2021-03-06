package com.msgpush;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PushMsg
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/13
 * @Version V1.0
 **/
@Data
public class PushMsg{
    private String serviceId;
    private String deviceId;
    private Object data;
    private Date date = new Date();


}
