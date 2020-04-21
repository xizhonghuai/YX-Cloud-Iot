package com.transmission.server.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msgpush.PushMsg;
import com.toolutils.ConstantUtils;
import org.apache.mina.core.session.IoSession;

import java.util.*;

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

    public IotSession(IoSession session,String serviceId,String connectType) {
        this.session = session;
        session.setAttribute("forwardMessageContainer", new LinkedList<>());
        forwardMessageContainer = (List<Object>)session.getAttribute("forwardMessageContainer");
        session.setAttribute("connectProperty",new ConnectProperty());
        connectProperty = (ConnectProperty) session.getAttribute("connectProperty");
        connectProperty.setAddress(session.getRemoteAddress().toString());
        connectProperty.setServiceId(serviceId);
        connectProperty.setType(connectType);

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

        Map<Long, IoSession> managedSessions = session.getService().getManagedSessions();
//        Long sessionKey = WriteMsgUtils.regIdToSessionKey(managedSessions, deviceId);
//        if (sessionKey != null){
//            managedSessions.remove(sessionKey);
//        }

        IoSession sessionOld = WriteMsgUtils.regIdToSession(managedSessions, deviceId);
        if (sessionOld != null){
            sessionOld.close(true);
        }
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

//    public void setServiceId(String serviceId) {
//        connectProperty.setServiceId(serviceId);
//    }


    public String getRemoteAddress() {
        return connectProperty.getAddress();
    }

    public String getDevice(){
        return getDeviceId()+getRemoteAddress();
    }

//    public void setConnectType(String type) {
//        connectProperty.setType(type);
//    }

    public String getConnectType() {
        return connectProperty.getType();
    }

    public void updateActivityTime() {
        connectProperty.setActivityTime(new Date());
    }

    public Object getForwardMessage() {
        if (forwardMessage == null){
            return null;
        }
        PushMsg pushMsg = new PushMsg();
        pushMsg.setServiceId(getServiceId());
        pushMsg.setDeviceId(getDeviceId());
        pushMsg.setData(forwardMessage);
        return pushMsg;
    }

    public void clearForwardMessage(){
        this.forwardMessage = null;
    }

    public void writeForwardMessage(Object forwardMessage) {
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

    public void clearDBMessage(){
        this.toDBMessage = null;
    }

    public void writeToDBMessage(Object toDBMessage) {
        this.toDBMessage = toDBMessage;
    }

    public void ack(Object msg){
        HashMap<String,Object> map = new HashMap<>();
        map.put("deviceId",getDeviceId());
        map.put("date",new Date());
        map.put("ack",msg);
        session.write(JSON.toJSONString(map));
    }

    public void ack(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("deviceId",getDeviceId());
        map.put("date",new Date());
        map.put("ack","ok");
        session.write(JSON.toJSONString(map));
    }

}
