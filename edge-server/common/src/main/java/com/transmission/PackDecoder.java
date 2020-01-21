package com.transmission;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * @ClassName PackDecoder
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
public class PackDecoder extends CumulativeProtocolDecoder {

    private final Charset charset;

    public PackDecoder(Charset charset) {
        this.charset = charset;

    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        String s = charset.decode(in.buf()).toString();
        if (s.isEmpty()) {
            return false;
        }
        out.write(s);
        return true;
    }
}
