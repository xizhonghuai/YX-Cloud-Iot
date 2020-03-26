/**
 * 
 */
package com.transmission.server.debug;

import com.alibaba.fastjson.JSON;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.ServerUtils;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @author xizhonghuai
 * @readme
 */
public class PushFilter extends IoFilterAdapter {

	private String serviceId;

	/**
	 * @param serviceId
	 */
	public PushFilter(String serviceId) {
		super();
		this.serviceId = serviceId;
	}

	//from device
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		nextFilter.messageReceived(session, message);

		AbstractBootServer server = (AbstractBootServer) ServerUtils.getServer(serviceId);
		if (server != null){

			String regId = (String) session.getAttribute("regId", null);
			if (regId == null) {
				regId = "no regId";
			}
			DebugMsg debugMsg = new DebugMsg(serviceId, regId, message);

			new PushService(server.getPushUrl(), JSON.toJSONString(debugMsg)).start();
		}










	}
}