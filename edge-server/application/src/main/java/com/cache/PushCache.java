package com.cache;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.model.PushDO;
import com.msgpush.MessagePush;
import com.msgpush.http.HttpPush;
import com.protocol.debug.push.WebSocketPush;
import com.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PushCache
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/17
 * @Version V1.0
 **/
@Component
public class PushCache {

    @Autowired
    private PushService pushService;


    private List<MessagePush> getPushList(String code) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("authCode", code);
        List<PushDO> pushList = pushService.select(map);
        List<MessagePush> messagePushList = new ArrayList<>();
        pushList.forEach(pushDO -> {
            if ("WS".equals(pushDO.getProtocol())) {
                messagePushList.add(new WebSocketPush());
            } else if ("HTTP".equals(pushDO.getProtocol())) {
                MessagePush httpPush = new HttpPush();
                httpPush.args = JSON.parseObject(pushDO.getParams(), HashMap.class);
                httpPush.init();
                messagePushList.add(httpPush);
            } else {

            }
        });
        return messagePushList;
    }

    private CacheLoader<String, List<MessagePush>> cacheLoader = new CacheLoader<String, List<MessagePush>>() {
        @Override
        public List<MessagePush> load(String code) throws Exception {
            return getPushList(code);
        }
    };

    private LoadingCache<String, List<MessagePush>> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)//指定缓存在5分钟内未被读写将被回收
            .refreshAfterWrite(10, TimeUnit.MINUTES)//继上一次更新缓存后多久回收
            .maximumSize(3000)
            .build(cacheLoader);

    public  List<MessagePush> get(String code) {
        try {
            return  cache.get(code);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


}
