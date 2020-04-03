package client.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName TMMqttClient
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
public class TMMqttClient {

    private static MqttClient client;
    private static String clientId;
    private static MqttConnectOptions options;
    private static ScheduledExecutorService scheduler;

    public static String host;
    public static String topic;

    private static String userName;
    private static String passWord;

    static {
        clientId = UUID.randomUUID().toString();
    }

    public static void init(String userName,String passWord,String host,String topic) {
        TMMqttClient.userName = userName;
        TMMqttClient.passWord = passWord;
        TMMqttClient.host = host;
        TMMqttClient.topic = topic;
    }


    private static void connect() throws MqttException {
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(host, clientId, new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());

        // setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        // 		options.setWill(topic, "close".getBytes(), 2, true);
        options.setMqttVersion(4);

        client.connect(options);
    }

    public static void publish(String msg) throws MqttException {

        if(client.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(1); //默认1
            mqttMessage.setPayload(msg.getBytes());
            MqttTopic topic = client.getTopic(TMMqttClient.topic);
            if(null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if(!publish.isComplete()) {
                        //log.info("消息发布成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }else {
            connect();
        }
    }








}
