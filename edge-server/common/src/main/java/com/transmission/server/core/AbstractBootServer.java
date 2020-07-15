package com.transmission.server.core;

import com.transmission.business.BusinessHandler;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.eclipse.paho.client.mqttv3.MqttClient;

import java.util.Map;

/**
 * @ClassName AbstractBootServer
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
public abstract class AbstractBootServer {

    public BootServerParameter bootServerParameter;
    public Boolean isStatus = false;
    public BusinessHandler businessHandler;

    /*tcp/udp*/
    public IoAcceptor ioAcceptor;
    /*usart*/
    public IoConnector serialConnector;
    public ConnectFuture future;
    /*mqtt*/
    public MqttClient client;


    public AbstractBootServer() {
    }

    public AbstractBootServer(BootServerParameter bootServerParameter)  {
        this.bootServerParameter = bootServerParameter;
        if (bootServerParameter.getHandler() != null){
            this.businessHandler = bootServerParameter.getHandler().businessHandler;
        }

    }



    public abstract   boolean init();

    public abstract boolean start();

    public abstract void stop();

    public  boolean rest(){
        stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return start();
    }


    public boolean getStatus() {
        return this.isStatus;
    }


    public boolean isDebug() {
        return this.bootServerParameter.isDebug();
    }



    public boolean isPush() {
        return this.bootServerParameter.isPush();
    }


    public IoSession getSession() {
        return this.future.getSession();
    }


    public String getPushUrl() {
        return this.bootServerParameter.getPushUrl();
    }


    public Map<Long, IoSession> getManagedSessions() {
        return this.ioAcceptor.getManagedSessions();
    }


    public BootServerParameter getBootServerParameter() {
        return this.bootServerParameter;
    }


    public String getServiceId() {
        return this.bootServerParameter.getServiceId();
    }

    public MqttClient getClient() {
        return client;
    }
}
