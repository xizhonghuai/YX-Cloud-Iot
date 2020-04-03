package com.init;

import com.Dao;
import com.manage.ServerManage;
import com.toolutils.ConstantUtils;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.debug.DebugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Initialization
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Slf4j
@Component
public class Initialization {


    @Value("${sysConf.resourcePath}")
    private String resourcePath;

    @Value("${sysConf.handlerJarFileBasePath}")
    private String handlerJarFileBasePath;

    @Value("${sysConf.decodePluginBasePath}")
    private String decodePluginBasePath;

    @Value("${sysConf.defaultServerPort}")
    private Integer defaultServerPort;

    @Value("${sysConf.debugPort}")
    private Integer debugPort;




    private String curHandler;
    private String curDecodePlugin;
    private String curDecodePluginClass;

    @Autowired
    private Dao dao;

    @Autowired
    private ServerManage serverManage;




    @PostConstruct
    public void init(){
       // todo 加载数据

        try {
            //启动调试服务
            new DebugService(debugPort).start();
            //数据库初始化
            dao.init();


            //todo 默认启动一个服务，后续删除
            BootServerParameter bootServerParameter = new BootServerParameter();
            bootServerParameter.setIdle(300);
            bootServerParameter.setPort(Arrays.asList(defaultServerPort));
            bootServerParameter.setDebug(true);
            bootServerParameter.setServiceId("ser");
            bootServerParameter.setServerType(ConstantUtils.TCP);
            try {
                serverManage.createService(bootServerParameter);
                serverManage.startServer("ser");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error(e.toString());
        }








    }

    public String getHandlerJarFileBasePath() {
        return handlerJarFileBasePath;
    }

    public String getDecodePluginBasePath() {
        return decodePluginBasePath;
    }

    public String getCurHandler() {
        return curHandler;
    }

    public String getCurDecodePlugin() {
        return curDecodePlugin;
    }

    public void setCurHandler(String curHandler) {
        this.curHandler = curHandler;
    }

    public void setCurDecodePlugin(String curDecodePlugin) {
        this.curDecodePlugin = curDecodePlugin;
    }

    public String getCurDecodePluginClass() {
        return curDecodePluginClass;
    }

    public void setCurDecodePluginClass(String curDecodePluginClass) {
        this.curDecodePluginClass = curDecodePluginClass;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
