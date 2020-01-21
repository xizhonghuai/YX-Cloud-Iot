package com.transmission;

/**
 * @ClassName MyHandler
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/16
 * @Version V1.0
 **/
public class MyHandler implements BusinessHandler {

    public Boolean init() {

        System.out.println("初始化");
        return true;
    }

    public void destroy() {
        System.out.println("关闭");
    }

    public void messageReceived(IotSession iotSession, Object message) {

        iotSession.sendMsg("ok");

        System.out.println(message.toString());

    }

    public void sessionOpened(IotSession iotSession) {

        iotSession.setAuthCode("0001");
        iotSession.setDeviceId("0001");



    }

    public void sessionClosed(IotSession iotSession) {

    }

    public void sessionIdle(IotSession iotSession) {

    }

    public void forward(Object message) {

    }
}
