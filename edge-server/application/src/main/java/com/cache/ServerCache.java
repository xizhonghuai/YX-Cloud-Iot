package com.cache;

import com.model.IotNodeDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ServerCache
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/8
 * @Version V1.0
 **/
public class ServerCache {

    private static volatile Map<String, IotNodeDO> serverDoMap = new HashMap<>();

    public static void add(IotNodeDO serverDo) {
        serverDoMap.put(serverDo.getServiceId(), serverDo);
    }

    public static void remove(String serviceId) {
        serverDoMap.remove(serviceId);
    }

    public static IotNodeDO get(String serviceId){
        return serverDoMap.get(serviceId);
    }

    public static void clear() {
        serverDoMap.clear();
    }

    public static Map<String, IotNodeDO> getServerMap() {
        return serverDoMap;
    }
}
