package com.business;

import com.alibaba.fastjson.JSON;
import com.toolutils.ConstantUtils;
import com.transmission.business.BusinessHandler;
import com.transmission.business.decodeplugin.Handler;
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

    @Override
    public void sessionOpened(IoSession session) {
        log.info(session.getRemoteAddress() + "连接");
        businessHandler.sessionOpened(new IotSession(session));
    }

    @Override
    public void sessionClosed(IoSession session) {
        businessHandler.sessionClosed(new IotSession(session));
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        businessHandler.sessionIdle(new IotSession(session));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        log.error(cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) {

        session.setAttribute(ConstantUtils.REG_STATUS, true);//todo debug
        if ((Boolean) session.getAttribute(ConstantUtils.REG_STATUS, false)) {
            businessHandler.messageReceived(new IotSession(session), message);
            businessHandler.forward(message);
        } else {

            try {
                RegMsg regMsg = (RegMsg) JSON.parse(message.toString());
                {
                    //todo device 授权校验逻辑
                }


            } catch (Exception e) {
                session.write("unknown device active close");
                session.close(true);
            }

        }
    }


}
