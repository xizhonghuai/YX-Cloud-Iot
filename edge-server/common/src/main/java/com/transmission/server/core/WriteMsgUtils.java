package com.transmission.server.core;

import com.toolutils.ConstantUtils;
import org.apache.mina.core.session.IoSession;

import java.util.Map;


/**
 * @ClassName WriteMsgUtils
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/14
 * @Version V1.0
 **/
public class WriteMsgUtils {


    public static void sendMsg(IoSession session, Object msg) {
        if (session != null) {
            session.write(msg);
        }
    }


    public static IoSession regIdToSession(Map<Long, IoSession> mapList, String regId) {
        IoSession session;
        for (Map.Entry<Long, IoSession> entry : mapList.entrySet()) {
            session = entry.getValue();
            if (regId.equals(session.getAttribute(ConstantUtils.REG_ID, null))) {
                return session;
            }

        }
        return null;
    }


    public static void sendMsg(Map<Long, IoSession> mapList, Object msg) {
        mapList.forEach((aLong, ioSession) -> sendMsg(ioSession,msg));
    }

    public static void sendMsg(Map<Long, IoSession> mapList, Object msg, String regId) {
        IoSession session;
        session = regIdToSession(mapList, regId);
        sendMsg(session, msg);
    }


}
