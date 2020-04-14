package com.transmission.server.core;

import com.toolutils.ConstantUtils;
import org.apache.mina.core.session.IoSession;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName IotSession
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
public class IotSession {


    private IoSession session;
    private List<Object> forwardMessageContainer;
    private Object forwardMessage;
    private Object toDBMessage;
    private ConnectProperty connectProperty;

    public IotSession(IoSession session) {
        this.session = session;
        session.setAttribute("forwardMessageContainer", new LinkedList<>());
        session.setAttribute("connectProperty",new ConnectProperty());
        connectProperty = (ConnectProperty) session.getAttribute("connectProperty");
        connectProperty.setAddress(session.getRemoteAddress().toString());
    }


    public void setAttribute(Object key, Object value) {
        session.setAttributeIfAbsent(key, value);
    }

    public Object getAttribute(Object key, Object defaultValue) {
        return session.getAttribute(key, defaultValue);
    }


    public void sendMsg(Object msg) {
        WriteMsgUtils.sendMsg(session, msg);
    }

    public void sendMsg(String regId, Object msg) {
        WriteMsgUtils.sendMsg(session.getService().getManagedSessions(), msg, regId);
    }

    public void sendMsg(IotSession iotSession, Object msg) {
        if (iotSession != this) {
            iotSession.sendMsg(msg);
        }
    }

    public void close() {
        this.session.close(true);
    }


    public void setAuthCode(String authCode) {
        setAttribute(ConstantUtils.AUTH_CODE, authCode);
        connectProperty.setAuthCode(authCode);
    }

    public String getAuthCode() {
        return (String) getAttribute(ConstantUtils.AUTH_CODE, null);
    }

    public void setDeviceId(String deviceId) {
        setAttribute(ConstantUtils.REG_ID, deviceId);
        connectProperty.setRegId(deviceId);
        connectProperty.setRegisterTime(new Date());
    }

    public String getDeviceId() {
        return (String) getAttribute(ConstantUtils.REG_ID, null);
    }

    public String getServiceId() {
        return connectProperty.getServiceId();
    }

    public void setServiceId(String serviceId) {
        connectProperty.setServiceId(serviceId);
    }


    public String getRemoteAddress() {
        return connectProperty.getAddress();
    }

    public String getDevice(){
        return getDeviceId()+getRemoteAddress();
    }

    public void setConnectType(String type) {
        connectProperty.setType(type);
    }

    public String getConnectType() {
        return connectProperty.getType();
    }

    public void updateActivityTime() {
        connectProperty.setActivityTime(new Date());
    }

    public Object getForwardMessage() {
        return forwardMessage;
    }

    public void setForwardMessage(Object forwardMessage) {
        this.forwardMessage = forwardMessage;
    }

    public void appendForwardMessageContainer(Object forwardMessage) {
        forwardMessageContainer.add(forwardMessage);
    }


    public void forwardMessageContainerCommit() {
        if (forwardMessageContainer != null) {
            forwardMessage = forwardMessageContainer;
            forwardMessageContainer.clear();
        }
    }

    public void clearForwardMessageContainer() {
        forwardMessageContainer.clear();
    }

    public Object getToDBMessage() {
        return toDBMessage;
    }

    public void setToDBMessage(Object toDBMessage) {
        this.toDBMessage = toDBMessage;
    }
}
