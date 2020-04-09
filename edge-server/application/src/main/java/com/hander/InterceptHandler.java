package com.hander;

import com.alibaba.fastjson.JSON;
import com.cache.DeviceShadow;
import com.init.SpringUtil;
import com.model.DeviceMsgDO;
import com.msgpush.MessagePush;
import com.msgpush.http.HttpPush;
import com.service.DeviceMsgService;
import com.toolutils.ConstantUtils;
import com.transmission.business.BusinessHandler;
import com.transmission.business.Handler;
import com.transmission.server.core.IotSession;
import com.transmission.server.core.RegMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @ClassName InterceptHandler
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
@Slf4j
public class InterceptHandler extends Handler {

    public InterceptHandler(BusinessHandler businessHandler) {
        super(businessHandler);
    }

    private IotSession iotSession;

    private String serviceId;


    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public void sessionOpened(IoSession session) {
        log.info(session.getRemoteAddress() + "连接");
        iotSession = new IotSession(session);
        iotSession.setServiceId(serviceId);
        businessHandler.sessionOpened(iotSession);
    }

    @Override
    public void sessionClosed(IoSession session) {
        businessHandler.sessionClosed(iotSession);
        iotSession = null;
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        businessHandler.sessionIdle(iotSession);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        log.error(cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) {

        if (!(Boolean) session.getAttribute(ConstantUtils.REG_STATUS, false)) {
            try {
                RegMsg regMsg = (RegMsg) JSON.parseObject(message.toString(), RegMsg.class);
                if (!StringUtils.isEmpty(regMsg.getRegId())) {

                    //todo device 授权校验逻辑
                    iotSession.setDeviceId(regMsg.getRegId());
                    session.write("reg ok");
                    session.setAttribute(ConstantUtils.REG_STATUS, true);
                } else {
                    session.write("Please send the registration package.");
                }
            } catch (Exception e) {
                session.write("JSON validation error.");
                //   session.close(true);
                e.printStackTrace();
            }

            return;
        }


        businessHandler.messageReceived(iotSession, message);

        //todo 业务数据多协议转发
        MessagePush messagePush = new HttpPush();
        messagePush.push(iotSession.getForwardMessage());


        //设备数据 记录
        if (iotSession.getToDBMessage() != null) {
            DeviceMsgService deviceMsgService = (DeviceMsgService) SpringUtil.getBean("deviceMsgService");
            DeviceMsgDO deviceMsgDO = new DeviceMsgDO();
            deviceMsgDO.setServiceId(iotSession.getServiceId());
            deviceMsgDO.setDeviceId(iotSession.getDeviceId());
            deviceMsgDO.setMsgBody(JSON.toJSONString(iotSession.getToDBMessage()));
            deviceMsgDO.setCreateDate(new Date());
            deviceMsgService.insert(deviceMsgDO);
            //insert message
        }


        {  //todo 设备log 记录

            //insert message


        }


    }
}
