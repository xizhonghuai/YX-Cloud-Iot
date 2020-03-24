package com.transmission.business.decodeplugin;

import com.transmission.server.core.IotSession;

public interface MessageDecodePlugin {


    void messageReceived(IotSession iotSession, Object message);

    void messageForward(Object message);

}
