package com.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DeviceShadow
 * @Description: TODO 缓存设备最新状态,后续使用redis替换
 * @Author xizhonghuai
 * @Date 2020/3/26
 * @Version V1.0
 **/

public class DeviceShadow {


    private DeviceShadow() {
    }

    public static volatile Map<String,Object> shadow = new ConcurrentHashMap<>();
}
