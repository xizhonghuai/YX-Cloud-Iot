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

/**
 * @ClassName DefaultMessageDecode
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Slf4j
public class DefaultMessageDecode implements MessageDecodePlugin {


    private DeviceMsgService deviceMsgService;

    public DefaultMessageDecode() {

        deviceMsgService = (DeviceMsgService) SpringUtil.getBean("deviceMsgService");
    }

    @Override
    public void messageReceived(IotSession iotSession, Object message) {
        log.info( iotSession.getDevice()+":Received data:" + message.toString());
        iotSession.sendMsg(message.toString());
        try {
            MsgBody msgBody = (MsgBody) JSON.parseObject(message.toString(),MsgBody.class);
            DeviceMsgDO deviceMsgDO = new DeviceMsgDO();
            deviceMsgDO.setServiceId(iotSession.getServiceId());
            deviceMsgDO.setDeviceId(msgBody.getDeviceId());
//            deviceMsgDO.setDeviceId(iotSession.getDeviceId());
            deviceMsgDO.setMsgBody(JSON.toJSONString(msgBody));
            deviceMsgDO.setCreateDate(new Date());

            DeviceShadow.shadow.put(iotSession.getServiceId()+msgBody.getDeviceId(),msgBody);

            deviceMsgService.insert(deviceMsgDO);

        } catch (Exception e) {
            iotSession.sendMsg("err "+e.toString());
        }


    }

    @Override
    public void messageForward(Object message) {


    }
}
