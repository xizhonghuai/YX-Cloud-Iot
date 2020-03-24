package com;

import com.manage.ServerManage;
import com.transmission.server.core.BootServerParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CodeTest
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/16
 * @Version V1.0
 **/
public class CodeTest {



    public static void main(String[] args) {


        ServerManage serverManage = new ServerManage();

        BootServerParameter bootServerParameter = new BootServerParameter();
        bootServerParameter.setServiceId("001");
        List<Integer> ports = new ArrayList<>();
        ports.add(10380);
        bootServerParameter.setServerType("Tcp");
        bootServerParameter.setPort(ports);
        bootServerParameter.setIdle(30);
        bootServerParameter.setHandlerJarFile("file:\\F:\\JAVA\\YX-Cloud-Iot\\cloud-sdk\\edge-sdk\\target\\edge-sdk-1.0-SNAPSHOT.jar");
        bootServerParameter.setHandlerClassName("com.example.MyHandler");


        try {
            serverManage.createService(bootServerParameter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        serverManage.startServer(bootServerParameter.getServiceId());


    }
}
