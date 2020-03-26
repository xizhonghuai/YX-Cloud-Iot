package com.manage;

import com.businesshandler.DefaultBusinessHandler;
import com.businesshandler.YXHandler;
import com.toolutils.ConstantUtils;
import com.transmission.business.BusinessHandler;
import com.transmission.business.Handler;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.TcpServer;
import com.transmission.server.core.ServerUtils;
import lib.ToolUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @ClassName ServerManageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Component
public class ServerManage {


    public synchronized void createService(BootServerParameter bootServerParameter) throws Exception {

        if (!StringUtils.isEmpty(bootServerParameter.getHandlerClassName()) && !StringUtils.isEmpty(bootServerParameter.getHandlerJarFile())) {
            Class<?> clazz;
            try {
                clazz = ToolUtils.getClass(new File(bootServerParameter.getHandlerJarFile()), bootServerParameter.getHandlerClassName());
                YXHandler yxHandler = new YXHandler((BusinessHandler) clazz.newInstance());
                yxHandler.setServiceId(bootServerParameter.getServiceId());
                bootServerParameter.setHandler(yxHandler);
            } catch (Exception e) {
                new Exception("服务初始化错误" + e.getMessage());
            }
        } else {
            YXHandler yxHandler = new YXHandler( new DefaultBusinessHandler());
            yxHandler.setServiceId(bootServerParameter.getServiceId());
            bootServerParameter.setHandler(yxHandler);
        }
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
            if (null == ServerUtils.add(bootServerParameter.getServiceId(),cloudIotServer)) {
                 cloudIotServer.init();

                // todo 服务信息入库
                /*cloudIotServer.start();*/
            } else  throw new Exception("服务初始化错误");
        }
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
        return (AbstractBootServer) ServerUtils.getServer(serviceId);
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
        ServerUtils.remove(serviceId);
        {
            // todo del db
        }

    }


}
