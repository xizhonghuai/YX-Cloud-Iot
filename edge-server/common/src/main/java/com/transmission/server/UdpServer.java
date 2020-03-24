package com.transmission.server;

import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;

/**
 * @ClassName UdpServer
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
public class UdpServer extends AbstractBootServer {

    public UdpServer(BootServerParameter bootServerParameter) {
        super(bootServerParameter);
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public void stop() {

    }


}
