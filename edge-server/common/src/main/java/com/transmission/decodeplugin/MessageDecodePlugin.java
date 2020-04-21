package com.transmission.decodeplugin;

import com.transmission.server.core.IotSession;

public interface MessageDecodePlugin {


    void messageReceived(IotSession iotSession, Object message);



}
