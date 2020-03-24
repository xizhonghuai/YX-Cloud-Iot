package com.transmission.server.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ServerContainer
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class ServerContainer {

    private ServerContainer(){}

    public static volatile Map<String,Object> cloudIotServerMap = new ConcurrentHashMap<>();

}
