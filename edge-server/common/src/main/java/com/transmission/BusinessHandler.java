package com.transmission;

public interface BusinessHandler {

    Boolean init();

    void destroy();

    void messageReceived(IotSession iotSession, Object message);

    void sessionOpened(IotSession iotSession);

    void sessionClosed(IotSession iotSession);

    void sessionIdle(IotSession iotSession);

    void forward(Object message);

}
