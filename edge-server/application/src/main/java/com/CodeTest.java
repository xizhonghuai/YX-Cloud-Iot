package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.ServerManage;
import com.toolutils.ConstantUtils;
import com.transmission.server.MqttServer;
import com.transmission.server.TcpServer;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.core.ConnectProperty;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

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
        bootServerParameter.setHost("tcp://:8902");
        bootServerParameter.setUserName("xzh");
        bootServerParameter.setPassWord("123");
        bootServerParameter.setTopic("te");
        bootServerParameter.setMqttCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

                System.out.println(new String(message.getPayload()));



            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        AbstractBootServer bootServer = new MqttServer(bootServerParameter);
        bootServer.start();

//        MqttMessage mqttMessage = new MqttMessage("DDD".getBytes());
//        mqttMessage.setQos(0);
//        bootServer.getClient().publish("qq",mqttMessage);







    }
}
