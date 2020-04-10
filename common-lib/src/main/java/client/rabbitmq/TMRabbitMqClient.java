package client.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

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

    private static ExecutorService cachedThreadPool;

    public static void init(String host, String userName, String password, int port, String queue) throws IOException, TimeoutException {

        cachedThreadPool = Executors.newFixedThreadPool(2);

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


    public static void queryRevive(String queryName, Consumer<QueryCallbackMsg> queryCallback) throws IOException {

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String message = null;
                        try {
                            message = new String(body, "UTF-8");
                            QueryCallbackMsg callbackMsg = new QueryCallbackMsg(message, channel, envelope.getDeliveryTag());
                            queryCallback.accept(callbackMsg);
                        } catch (Exception e) {

                        }
                    }
                });
            }
        };

        channel.basicConsume(queryName, false, consumer);
    }






    public static void free() throws IOException, TimeoutException {

        // 关闭资源
        channel.close();
        conn.close();
    }




}
