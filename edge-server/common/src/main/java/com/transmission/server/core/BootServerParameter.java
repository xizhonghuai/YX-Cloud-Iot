package com.transmission.server.core;

import com.transmission.business.Handler;
import lib.UserToken;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BootServerParameter
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Data
public class BootServerParameter {

    private String serverName;
    private String serverType;
    private String handlerClassName;
    private String handlerJarFile;
    private String serviceId;
    private int idle;
    private String decodeCharset = "ISO-8859-1";
    private String encodeCharset = "ISO-8859-1";
    private boolean isDebug = false;
    private boolean isPush = false;
    private String pushUrl;
    private String toServiceId;
    private Handler handler;

    private List<Integer> port;
    private String comName;
    private Integer baud;


    private String host;
    private String topic;
    private String userName;
    private String passWord;

    private Date date = new Date();




}
