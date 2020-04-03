package client.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TMRabbitMqClient
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
public class TMRabbitMqClient {

    private static Channel channel;
    private static Connection conn;
    private static String queueName;

    public static void init(String host, String userName, String password, int port, String queue) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setHost(host);
        factory.setPort(port);
        factory.setVirtualHost("/");
        conn = factory.newConnection();
        channel = conn.createChannel();
        queueName = queue;
    }

    public static void push(String msg) throws IOException {
        channel.queueDeclare(queueName, true, false, false, null);
        //直接发数据到队列
        channel.basicPublish("", queueName,
                MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
    }

    public static void free() throws IOException, TimeoutException {

        // 关闭资源
        channel.close();
        conn.close();
    }




}
