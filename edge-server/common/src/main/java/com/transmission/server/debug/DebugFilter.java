/**
 * 
 */
package com.transmission.server.debug;

import com.alibaba.fastjson.JSON;
import com.toolutils.WriteMsgUtils;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.ServerUtils;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @author XIZHONGHUAI
 *
 */
public class DebugFilter extends IoFilterAdapter {

	private String serviceId;

	/**
	 * @param serviceId
	 */
	public DebugFilter(String serviceId) {
		super();
		this.serviceId = serviceId;
	}

	// from device
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		nextFilter.messageReceived(session, message);

		String msg = "";
		
		String regId = (String) session.getAttribute("regId", null);
		
		if (regId == null){
			regId = "no regId";
		}

		DebugMsg debugMsg = new DebugMsg(serviceId, regId, message);

		DebugService debugServer = (DebugService) ServerUtils.getServer("debug");

		if (debugServer != null){
			WriteMsgUtils.sendMsg(debugServer.getManagedSessions(),JSON.toJSONString(debugMsg));

		}


		// M2M
		{
			// TODO 需优化以下代码
		}
		try {
			DebugMsg M2MDebugMsg = (DebugMsg) JSON.parseObject(message.toString(),DebugMsg.class);
			AbstractBootServer server = (AbstractBootServer) ServerUtils.getServer(debugMsg.getServiceId());
			if (server != null){
				WriteMsgUtils.sendMsg(server.getManagedSessions(),M2MDebugMsg.getData(),M2MDebugMsg.getRegId());
			}
		} catch (Exception e) {

		}
	}
}
