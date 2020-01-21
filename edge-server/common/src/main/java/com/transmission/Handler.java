package com.transmission;

import com.toolutils.ConstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @ClassName Handler
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
@Slf4j
public class Handler extends IoHandlerAdapter {


    private BusinessHandler businessHandler;


    public Handler(BusinessHandler businessHandler) {
        this.businessHandler = businessHandler;
    }

    @Override
    public void sessionOpened(IoSession session)  {
        businessHandler.sessionOpened(new IotSession(session));
    }

    @Override
    public void sessionClosed(IoSession session) {
        businessHandler.sessionClosed(new IotSession(session));
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)  {
        businessHandler.sessionIdle(new IotSession(session));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        log.error(cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message){

        String authorizedCode  = (String) session.getAttribute(ConstantUtils.AUTH_CODE);
        if (authorizedCode == null) {
            session.write("unknown device active close");
            session.close(true);
            return;
        }
        {
            //todo device 授权校验逻辑
        }

        businessHandler.messageReceived(new IotSession(session), message);
        businessHandler.forward(message);
    }


}
