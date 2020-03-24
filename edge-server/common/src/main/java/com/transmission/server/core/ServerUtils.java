package com.transmission.server.core;

import java.awt.*;
import java.util.HashMap;

/**
 * @ClassName ServerUtils
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class ServerUtils {

    private ServerUtils() {
    }

    public static Object add(String id, Object server) {
        return ServerContainer.cloudIotServerMap.putIfAbsent(id, server);
    }

    public static Object getServer(String id){

        return  ServerContainer.cloudIotServerMap.get(id);
    }

    public static void remove(String id) {
        ServerContainer.cloudIotServerMap.remove(id);
    }

    public static Object getServers() {
        return ServerContainer.cloudIotServerMap;
    }
}
