package com.msgpush.http;

import client.http.TMHttpClient;
import com.alibaba.fastjson.JSON;
import com.msgpush.MessagePush;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName HttpPush
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
@Slf4j
public class HttpPush extends MessagePush {

    private TMHttpClient tmHttpClient;

    @Override
    public void init() {
//        tmHttpClient = new TMHttpClient();
//        try {
//            tmHttpClient.init();
//        } catch (IOReactorException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void push(Object message) {

//        tmHttpClient.doPost((String) args.getOrDefault("url", "http:127.0.0.1:8080/"), (HashMap<String, String>) args.get("header"), JSON.toJSONString(message), tmHttpCallbackPrams -> {
//          log.info(tmHttpCallbackPrams.toString());
//        });
    }

    @Override
    public void close() {

        try {
            tmHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
