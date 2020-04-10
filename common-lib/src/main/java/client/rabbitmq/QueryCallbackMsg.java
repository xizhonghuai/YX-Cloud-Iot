package client.rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @ClassName QueryCallbackMsg
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/10
 * @Version V1.0
 **/
public class QueryCallbackMsg {

    private String msg;
    private Channel channel;
    private long deliveryTag;

    public QueryCallbackMsg(String msg, Channel channel, long deliveryTag) {
        this.msg = msg;
        this.channel = channel;
        this.deliveryTag = deliveryTag;
    }

    public String getMsg() {
        return msg;
    }

    public Channel getChannel() {
        return channel;
    }

    public long getDeliveryTag() {
        return deliveryTag;
    }

    public void basicAck() throws IOException {
        this.channel.basicAck(this.deliveryTag, false);
    }

    public void basicReject(boolean isResend) throws IOException {
        channel.basicReject(this.deliveryTag, isResend);
    }
}
