/**
 * 
 */
package com.transmission.server.debug;


import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.CodecFactory;
import com.transmission.server.core.ServerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author XIZHONGHUAI
 *
 */
@Slf4j
public class DebugService extends AbstractBootServer {

	private IoAcceptor ioAcceptor = null;
	private Integer port;

	public DebugService(Integer port) {
		this.port = port;
		init();
	}





	public boolean init() {
		try {

			this.ioAcceptor = new NioSocketAcceptor();

			// 设置过滤器
			ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
					new CodecFactory(Charset.forName("ISO-8859-1"), Charset.forName("ISO-8859-1"))));

			// 设置读取缓冲区最大值
			this.ioAcceptor.getSessionConfig().setMaxReadBufferSize(2048);
			// 读写通道X秒内无操作进入空闲状态
			this.ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 300);
			// 绑定逻辑处理器
			ioAcceptor.setHandler(new DebugHandler());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("debug初始化错误：" + e.getMessage());
			return false;
		}
		return true;
	}


	public boolean start() {

		init();
		try {
			ioAcceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		ServerUtils.add("debug",this);
		return true;
	}


	public void stop() {

	}

	public Map<Long, IoSession> getManagedSessions() {
		return this.ioAcceptor.getManagedSessions();
	}


}
