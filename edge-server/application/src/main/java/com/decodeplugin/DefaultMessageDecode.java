package com.decodeplugin;

import com.alibaba.fastjson.JSON;
import com.cache.DeviceShadow;
import com.init.SpringUtil;
import com.message.MsgBody;
import com.model.DeviceMsgDO;
import com.service.DeviceMsgService;
import com.transmission.decodeplugin.MessageDecodePlugin;
import com.transmission.server.core.IotSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
