package com;

import com.alibaba.fastjson.JSON;
import com.manage.ServerManage;
import com.toolutils.ConstantUtils;
import com.transmission.server.MqttServer;
import com.transmission.server.TcpServer;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.core.ConnectProperty;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CodeTest
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/16
 * @Version V1.0
 **/
public class CodeTest {



    public static void main(String[] args) throws MqttException {

        BootServerParameter bootServerParameter = new BootServerParameter();
        bootServerParameter.setServerName("YX-Clout-Iot");
        bootServerParameter.setServiceId("ser");
        bootServerParameter.setHost("tcp://39.98.164.168:8902");
        bootServerParameter.setUserName("xzh");
        bootServerParameter.setPassWord("123");
        bootServerParameter.setTopic("a");
        AbstractBootServer bootServer = new MqttServer(bootServerParameter);
        bootServer.start();


        bootServer.getClient().close();

    }
}
