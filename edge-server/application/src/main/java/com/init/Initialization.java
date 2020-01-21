package com.init;

import com.transmission.AbstractBootServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
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

    public static volatile Map<String,AbstractBootServer> cloudIotServerMap = new ConcurrentHashMap<>();

    @Value("${sysConf.jarBasePath}")
    private String jarBasePath;

    private String curJar;




    @PostConstruct
    public void init(){
       // todo 加载数据
    }

    public String getJarBasePath() {
        return jarBasePath;
    }

    public String getCurJar() {
        return curJar;
    }

    public void setCurJar(String curJar) {
        this.curJar = curJar;
    }
}
