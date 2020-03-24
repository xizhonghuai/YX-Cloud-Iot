/**
 * 
 */
package com.transmission.server.debug;

import com.alibaba.fastjson.JSON;
import com.toolutils.WriteMsgUtils;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.ServerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author XIZHONGHUAI
 *
 */
@Slf4j
public class DebugHandler extends IoHandlerAdapter {

	//from user
	public void messageReceived(IoSession session, Object message)  {
		// Empty handler
		try {

			DebugMsg debugMsg = (DebugMsg) JSON.parseObject(message.toString(),DebugMsg.class);
			AbstractBootServer server = (AbstractBootServer) ServerUtils.getServer(debugMsg.getServiceId());

			if (server == null){
				session.write("error:" + debugMsg.getServiceId() + ",not found");
				return;
			}

			if (server.isDebug()) {
				IoSession eqSession = WriteMsgUtils.regIdToSession(server.getManagedSessions(),debugMsg.getRegId());
				if (eqSession != null) {
					eqSession.write(debugMsg.getData());
				} else {
					session.write("error:" + debugMsg.getRegId() + ",Off-line");
				}
			} else {
				session.write("error:" + debugMsg.getServiceId() + ",not debug");
			}

		} catch (Exception e) {
			session.write("error:" + "Format error,eg:\r\n"+JSON.toJSONString(new DebugMsg("sid","0001","010110110101"))+":\r\n");
		}

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

		session.write("active close");
		session.close(true);
	}

}
