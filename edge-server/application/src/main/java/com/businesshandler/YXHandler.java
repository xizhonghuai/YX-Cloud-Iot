package com.businesshandler;

import com.alibaba.fastjson.JSON;
import com.toolutils.ConstantUtils;
import com.transmission.business.BusinessHandler;
import com.transmission.business.Handler;
import com.transmission.server.core.IotSession;
import com.transmission.server.core.RegMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @ClassName HandlerBase
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
@Slf4j
public class YXHandler extends Handler {

    public YXHandler(BusinessHandler businessHandler) {
        super(businessHandler);
    }

    private IotSession iotSession;

    private String serviceId;


    public void setServiceId(String serviceId){
       this.serviceId  = serviceId;
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

        session.setAttribute(ConstantUtils.REG_STATUS, true);//todo debug
        if ((Boolean) session.getAttribute(ConstantUtils.REG_STATUS, false)) {
            businessHandler.messageReceived(iotSession, message);
            businessHandler.forward(message);

            {
                //todo 业务数据多协议转发
            }
        } else {

            try {
                RegMsg regMsg = (RegMsg) JSON.parse(message.toString());
                {
                     iotSession.setDeviceId(regMsg.getRegId());

                    //todo device 授权校验逻辑
                }


            } catch (Exception e) {
                session.write("unknown device active close");
                session.close(true);
            }

        }
    }


}
