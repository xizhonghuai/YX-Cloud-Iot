package com.transmission.server.core;

import com.alibaba.fastjson.JSON;
import com.toolutils.ConstantUtils;
import com.toolutils.WriteMsgUtils;
import org.apache.mina.core.session.IoSession;

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

    public IotSession(IoSession session) {
        this.session = session;
        if (session.getAttribute("forwardMessageContainer") == null) {
            session.setAttribute("forwardMessageContainer", new LinkedList<>());
        } else {
            forwardMessageContainer = (List<Object>) session.getAttribute("forwardMessageContainer");
        }
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
    }

    public String getAuthCode() {
        return (String) getAttribute(ConstantUtils.AUTH_CODE, null);
    }

    public void setDeviceId(String deviceId) {
        setAttribute(ConstantUtils.REG_ID, deviceId);
    }

    public String getDeviceId() {

        return (String) getAttribute(ConstantUtils.REG_ID,null);
    }

    public String getServiceId() {
        return (String) session.getAttribute(ConstantUtils.SERVICE_ID);
    }

    public void setServiceId(String serviceId) {
        session.setAttribute(ConstantUtils.SERVICE_ID,serviceId);
    }



    public String getRemoteAddress() {

        return session.getRemoteAddress().toString();
    }

    public String getDevice() {
        return getDeviceId() + "/" + getRemoteAddress();

    }

    public Boolean insertDataBase(Object data) {
        String jsonStr = JSON.toJSONString(data);

        // TODO 数据入库逻辑，后续完善
        return true;
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

}
