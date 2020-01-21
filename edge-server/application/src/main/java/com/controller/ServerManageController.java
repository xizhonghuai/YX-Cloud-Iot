package com.controller;

import com.init.Initialization;
import com.manage.ServerManage;
import com.transmission.BootServerParameter;
import io.swagger.annotations.*;
import lib.FileUntis;
import lib.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName ServerManageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Slf4j
@RestController
public class ServerManageController {

    @Autowired
    private ServerManage serverManage;

    @Autowired
    private Initialization initialization;


    @ApiOperation(value = "jar包上传", httpMethod = "POST")
    @RequestMapping(value = "/jarUpload", method = RequestMethod.POST)
    public RestResult jarUpload(@RequestParam("file") MultipartFile file){

        if (file.isEmpty()) {
            return new RestResult("file is null");
        }

        String fileName = file.getOriginalFilename();
        String filePath = initialization.getJarBasePath()+"/"+ UUID.randomUUID().toString()+"/";
        File dest = new File(filePath + fileName);
        FileUntis.createParentPath(dest);
        try {
            file.transferTo(dest);
            log.info(fileName+"-----------上传成功");
        } catch (IOException e) {
            log.error(e.toString(), e);
            return new RestResult("上传失败"+e.getMessage());
        }
        initialization.setCurJar(filePath+fileName);
        return new RestResult();
    }


    @ApiOperation(value = "服务创建DEMO", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "query", name = "port", dataType = "Integer", required = true, value = "监听端口"),
            @ApiImplicitParam(paramType = "query", name = "idle", dataType = "Integer", required = true, value = "读写空闲时间"),
            @ApiImplicitParam(paramType = "query", name = "handlerClassName", dataType = "String", required = true, value = "执行逻辑处理类名称")
    })

    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/createServiceDemo", method = RequestMethod.POST)
    public RestResult createServiceDemo(@RequestParam("serviceId") String serviceId,
                                        @RequestParam("port") Integer port,
                                        @RequestParam("idle") Integer idle,
                                        @RequestParam("handlerClassName") String handlerClassName
                                        ){


        BootServerParameter bootServerParameter = new BootServerParameter();
        bootServerParameter.setServerType("Tcp");
        bootServerParameter.setServiceId(serviceId);
        List<Integer> ports = new ArrayList<>();
        ports.add(port);
        bootServerParameter.setPort(ports);
        bootServerParameter.setIdle(idle);
        bootServerParameter.setHandlerJarFile(initialization.getCurJar());
        bootServerParameter.setHandlerClassName(handlerClassName);

        try {
            serverManage.createService(bootServerParameter);
        } catch (Exception e) {

            e.printStackTrace();
            return new RestResult("创建失败"+e.getMessage());
        }

        return new RestResult();
    }


    @ApiOperation(value = "服务创建", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/createService", method = RequestMethod.POST)
    public RestResult createService(@RequestBody BootServerParameter bootServerParameter){

        System.out.println(bootServerParameter);
        return new RestResult();
    }



    @ApiOperation(value = "启动服务", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/startServer", method = RequestMethod.POST)
    public RestResult startServer(@RequestParam("serviceId") String serviceId){
        serverManage.startServer(serviceId);
        return new RestResult();
    }

    @ApiOperation(value = "停止服务", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/stopServer", method = RequestMethod.POST)
    public RestResult stopServer(@RequestParam("serviceId") String serviceId){
        serverManage.stopServer(serviceId);
        return new RestResult();
    }














}
