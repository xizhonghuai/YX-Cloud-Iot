package com.transmission;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * @ClassName CodecFactory
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
public class CodecFactory  implements ProtocolCodecFactory {

    private Charset decodeCharset; // 解码字符集
    private Charset encodeCharset; // 编码字符集

    public CodecFactory(Charset decodecharset, Charset encodecharset) {
        this.decodeCharset = decodecharset;
        this.encodeCharset = encodecharset;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new PackEncoder(encodeCharset);
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return  new PackDecoder(decodeCharset);
    }
}
