package com.business.decodeplugin;

import com.transmission.business.decodeplugin.MessageDecodePlugin;
import com.transmission.server.core.IotSession;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DefaultMessageDecode
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Slf4j
public class DefaultMessageDecode implements MessageDecodePlugin {
    @Override
    public void messageReceived(IotSession iotSession, Object message) {
        log.info("Received data:"+message.toString());
        iotSession.sendMsg(message.toString());
    }

    @Override
    public void messageForward(Object message) {



    }
}
