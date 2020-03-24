package com.init;

import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.debug.DebugService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Initialization
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Component
public class Initialization {

    @Value("${sysConf.resourcePath}")
    private String resourcePath;

    @Value("${sysConf.handlerJarFileBasePath}")
    private String handlerJarFileBasePath;

    @Value("${sysConf.decodePluginBasePath}")
    private String decodePluginBasePath;

    @Value("${sysConf.debugPort}")
    private Integer debugPort;


    private String curHandler;
    private String curDecodePlugin;
    private String curDecodePluginClass;




    @PostConstruct
    public void init(){
       // todo 加载数据
        new DebugService(debugPort).start();
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
