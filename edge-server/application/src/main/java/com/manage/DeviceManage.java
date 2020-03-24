package com.manage;

import com.init.Initialization;
import com.toolutils.ConstantUtils;
import com.toolutils.WriteMsgUtils;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.ServerUtils;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeviceManage
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
@Component
public class DeviceManage {


    public void sendCmd(String serviceId, Object cmd) {
        AbstractBootServer server = (AbstractBootServer) ServerUtils.getServer(serviceId);
        if (server != null) {
            WriteMsgUtils.sendMsg(server.getManagedSessions(), cmd);
        }
    }

    public void sendCmd(String serviceId, String redId, Object cmd) {
        AbstractBootServer server =  (AbstractBootServer) ServerUtils.getServer(serviceId);
        if (server != null) {
            WriteMsgUtils.sendMsg(server.getManagedSessions(), cmd, redId);
        }
    }


    public void disconnect(String serviceId, String regId) {
        AbstractBootServer server =  (AbstractBootServer) ServerUtils.getServer(serviceId);
        if (server != null) {
            IoSession ioSession = WriteMsgUtils.regIdToSession(server.getManagedSessions(), regId);
            if (ioSession != null) ioSession.close(true);
        }
    }

    public List<HashMap<String, Object>> getDevices(String serviceId) {
        AbstractBootServer server =  (AbstractBootServer) ServerUtils.getServer(serviceId);
        if (server != null) {
            List<HashMap<String, Object>> list = new ArrayList<>();
            Map<Long, IoSession> managedSessions = server.getManagedSessions();
            managedSessions.forEach((aLong, ioSession) -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put(ConstantUtils.REG_ID, ioSession.getAttribute(ConstantUtils.REG_ID));
                map.put("device", ioSession);
                list.add(map);
            });
            return list;
        }
        return null;
    }


}
