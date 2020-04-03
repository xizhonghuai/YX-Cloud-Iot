package com.transmission.server;

import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.core.CodecFactory;
import com.transmission.server.debug.DebugFilter;
import com.transmission.server.debug.PushFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName TcpServer
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Slf4j
public class TcpServer extends AbstractBootServer {


    public TcpServer(BootServerParameter bootServerParameter) {
        super(bootServerParameter);
    }

    @Override
    public boolean init() {

        try {

            this.ioAcceptor = new NioSocketAcceptor();

            BootServerParameter par = this.bootServerParameter;

            // 设置过滤器
            ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                    new CodecFactory(Charset.forName(par.getDecodeCharset()), Charset.forName(par.getEncodeCharset()))));

            if(par.isDebug()){
                ioAcceptor.getFilterChain().addLast("debug", new DebugFilter(par.getServiceId()));
            }

            if (par.isPush()){
                //todo 暂时屏蔽 提升性能
//                ioAcceptor.getFilterChain().addLast("push", new PushFilter(par.getServiceId()));
            }

            // 设置读取缓冲区最大值
            this.ioAcceptor.getSessionConfig().setMaxReadBufferSize(2048);
            // 读写通道X秒内无操作进入空闲状态
            this.ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, par.getIdle());
            // 绑定逻辑处理器
            ioAcceptor.setHandler(par.getHandler());
        } catch (Exception e) {
            // TODO: handle exception
            log.error("协议绑定失败：" + e.getMessage());
          return false;
        }
        return true;
    }


    @Override
    public boolean start() {
        init();
        List<Integer> ports = this.bootServerParameter.getPort();
        for (Integer port : ports){
            try {
                ioAcceptor.bind(new InetSocketAddress(port.intValue()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        this.businessHandler.init(); //启动成功执行用户初始化逻辑
        log.info("tcp服务启动成功...  端口号：" + this.bootServerParameter.getPort().toString());
        this.isStatus = true;
        return true;
    }

    @Override
    public void stop() {
        this.ioAcceptor.dispose();
        this.ioAcceptor = null;
        this.businessHandler.destroy();
        this.isStatus = false;
    }


}
