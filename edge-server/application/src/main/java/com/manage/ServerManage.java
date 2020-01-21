package com.manage;

import com.init.Initialization;
import com.toolutils.ConstantUtils;
import com.transmission.AbstractBootServer;
import com.transmission.BootServerParameter;
import com.transmission.TcpServer;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServerManageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Component
public class ServerManage {


    public synchronized Boolean createService(BootServerParameter bootServerParameter) throws Exception {
        AbstractBootServer cloudIotServer = null;
        String serverType = bootServerParameter.getServerType();
        if (ConstantUtils.TCP.equals(serverType)) {
            cloudIotServer = new TcpServer(bootServerParameter);
        } else if (ConstantUtils.UDP.equals(serverType)) {

        } else if (ConstantUtils.SERIAL.equals(serverType)) {

        } else {
            throw new Exception("服务类型出错");
        }
        if (cloudIotServer != null) {
            if (cloudIotServer.init() && cloudIotServer == Initialization.cloudIotServerMap.putIfAbsent(cloudIotServer.getServiceId(), cloudIotServer)) {
                // todo 服务信息入库

                /*cloudIotServer.start();*/
                return true;
            }
        }
        return false;
    }


    public synchronized boolean startServer(String serviceId) {
        AbstractBootServer cloudIotServer = getCloudIotServer(serviceId);
        if (cloudIotServer != null) {
            if (cloudIotServer.getStatus()) {
                return true;
            }
            return cloudIotServer.start();
        }
        return false;
    }

    public AbstractBootServer getCloudIotServer(String serviceId) {
        return Initialization.cloudIotServerMap.get(serviceId);
    }

    public void stopServer(String serviceId) {
        AbstractBootServer cloudIotServer = getCloudIotServer(serviceId);
        if (cloudIotServer != null){
            if (cloudIotServer.getStatus()) {
                cloudIotServer.stop();
            }
        }
    }

    public boolean restServer(String serviceId) {
        AbstractBootServer cloudIotServer = getCloudIotServer(serviceId);
        return cloudIotServer.rest();
    }

    public void delServer(String serviceId) {
        stopServer(serviceId);
        Initialization.cloudIotServerMap.remove(serviceId);
        {
            // todo del db
        }

    }


}
