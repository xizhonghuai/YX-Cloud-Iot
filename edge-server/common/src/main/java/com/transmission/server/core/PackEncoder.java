package com.transmission.server.core;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @ClassName PackEncoder
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
public class PackEncoder implements ProtocolEncoder {

    private final Charset charset;

    public PackEncoder(Charset charset) {

        this.charset=charset;
    }


    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

        IoBuffer buf = IoBuffer.allocate(2048).setAutoExpand(true);

        buf.put(charset.encode(message.toString()));

        buf.flip();

        out.write(buf);
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
