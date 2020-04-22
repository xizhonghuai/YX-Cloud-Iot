package com.hander.businesshandler.decodeplugin;

import com.alibaba.fastjson.JSON;
import com.cache.DeviceShadow;
import com.transmission.decodeplugin.MessageDecodePlugin;
import com.transmission.server.core.IotSession;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @ClassName DefaultMessageDecode
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Slf4j
public class DefaultMessageDecode implements MessageDecodePlugin {


    public DefaultMessageDecode() {

    }

    @Override
    public void messageReceived(IotSession iotSession, Object message) {
        log.info( iotSession.getDevice()+":Received data:" + message.toString());
        iotSession.ack();
        try {
//            MsgBody msgBody = (MsgBody) JSON.parseObject(message.toString(),MsgBody.class);
            HashMap<String,Object> msgMap = JSON.parseObject(message.toString(),HashMap.class);

            DeviceShadow.shadow.put(iotSession.getServiceId()+iotSession.getDeviceId(),msgMap);
            iotSession.writeToDBMessage(msgMap);
            iotSession.writeForwardMessage(message);


        } catch (Exception e) {
            iotSession.ack(e.toString());

        }


    }


}
