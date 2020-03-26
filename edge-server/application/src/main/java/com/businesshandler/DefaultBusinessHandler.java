package com.businesshandler;

import com.decodeplugin.DefaultMessageDecode;
import com.init.Initialization;
import com.init.SpringUtil;
import com.transmission.business.BusinessHandler;
import com.transmission.decodeplugin.MessageDecodePlugin;
import com.transmission.server.core.IotSession;
import lib.ToolUtils;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName DefaultBusinessHandler
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/18
 * @Version V1.0
 **/

public class DefaultBusinessHandler implements BusinessHandler {

    @Override
    public Boolean init() {
        return null;
    }

    @Override
    public void destroy() {

    }


    @Override
    public void messageReceived(IotSession iotSession, Object message) {

        if (((Initialization)SpringUtil.getBean("initialization")).getCurDecodePlugin() == null){
            MessageDecodePlugin defaultMessageDecode = new DefaultMessageDecode();
            defaultMessageDecode.messageReceived(iotSession,message);
            defaultMessageDecode.messageForward(message);
            {
                //todo 业务数据多协议转发
            }
        } else {

            try {
                Class<?> clazz = ToolUtils.getClass(new File(((Initialization)SpringUtil.getBean("initialization")).getCurDecodePlugin()), ((Initialization)SpringUtil.getBean("initialization")).getCurDecodePluginClass());
                MessageDecodePlugin decodePlugin = (MessageDecodePlugin) clazz.newInstance();
                decodePlugin.messageReceived(iotSession,message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sessionOpened(IotSession iotSession) {
        iotSession.setAuthCode(UUID.randomUUID().toString());
    }

    @Override
    public void sessionClosed(IotSession iotSession) {

    }

    @Override
    public void sessionIdle(IotSession iotSession) {
        iotSession.close();
    }

    @Override
    public void forward(Object message) {



    }
}
