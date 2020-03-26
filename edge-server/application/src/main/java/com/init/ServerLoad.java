package com.init;

import com.manage.ServerManage;
import com.toolutils.ConstantUtils;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.core.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName ServerLoad
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/26
 * @Version V1.0
 **/

@Component
public class ServerLoad {

    @Autowired
    private ServerManage serverManage;

    @PostConstruct
    public void load(){

        //todo 默认启动一个服务，后续删除
        BootServerParameter bootServerParameter = new BootServerParameter();
        bootServerParameter.setIdle(300);
        bootServerParameter.setPort(Arrays.asList(8085));
        bootServerParameter.setDebug(true);
        bootServerParameter.setServiceId("ser");
        bootServerParameter.setServerType(ConstantUtils.TCP);
        try {
            serverManage.createService(bootServerParameter);
            serverManage.startServer("ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
