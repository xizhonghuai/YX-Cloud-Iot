package com.controller;

import com.cache.ServerCache;
import com.init.Initialization;
import com.manage.ServerManage;
import com.model.IotNodeDO;
import com.toolutils.ConstantUtils;
import com.transmission.server.core.AbstractBootServer;
import com.transmission.server.core.BootServerParameter;
import io.swagger.annotations.*;
import lib.FileUntis;
import lib.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ServerManageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/server/manage/")
public class ServerManageController {

    @Autowired
    private ServerManage serverManage;

    @Autowired
    private Initialization initialization;


    @ApiOperation(value = "服务创建DEMO", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "query", name = "port", dataType = "Integer", required = true, value = "监听端口"),
            @ApiImplicitParam(paramType = "query", name = "idle", dataType = "Integer", required = true, value = "读写空闲时间"),
    })

    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/createServiceDemo", method = RequestMethod.POST)
    public RestResult createServiceDemo(@RequestParam("serviceId") String serviceId,
                                        @RequestParam("port") Integer port,
                                        @RequestParam("idle") Integer idle
    ) {
        BootServerParameter bootServerParameter = new BootServerParameter();
        bootServerParameter.setServerType(ConstantUtils.TCP);
        bootServerParameter.setServiceId(serviceId);
        List<Integer> ports = new ArrayList<>();
        ports.add(port);
        bootServerParameter.setPort(ports);
        bootServerParameter.setIdle(idle);

        try {
            serverManage.createService(bootServerParameter);
        } catch (Exception e) {

            e.printStackTrace();
            return new RestResult("创建失败" + e.getMessage());
        }

        return new RestResult();
    }


    @ApiOperation(value = "上传自定义业务处理handler包文件", httpMethod = "POST")
    @RequestMapping(value = "/handlerUpload", method = RequestMethod.POST)
    public RestResult handlerUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new RestResult("file is null");
        }

        String fileName = file.getOriginalFilename();
        String filePath = initialization.getHandlerJarFileBasePath() + "/" + UUID.randomUUID().toString() + "/";
        File dest = new File(filePath + fileName);
        FileUntis.createParentPath(dest);
        try {
            file.transferTo(dest);
            log.info(fileName + "-----------上传成功");
        } catch (IOException e) {
            log.error(e.toString(), e);
            return new RestResult("上传失败" + e.getMessage());
        }
        initialization.setCurHandler(filePath + fileName);
        return new RestResult();
    }

    @ApiOperation(value = "上传数据解码插件", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "name", dataType = "String", required = true, value = "解码插件类名称"),
    })
    @RequestMapping(value = "/pluginUpload/{name}", method = RequestMethod.POST)
    public RestResult pluginUpload(@RequestParam("file") MultipartFile file, @PathVariable("name") String name) {

        if (file.isEmpty()) {
            return new RestResult("file is null");
        }

        String fileName = file.getOriginalFilename();
        String filePath = initialization.getDecodePluginBasePath() + "/" + UUID.randomUUID().toString() + "/";
        File dest = new File(filePath + fileName);
        FileUntis.createParentPath(dest);
        try {
            file.transferTo(dest);
            log.info(fileName + "-----------上传成功");
        } catch (IOException e) {
            log.error(e.toString(), e);
            return new RestResult("上传失败" + e.getMessage());
        }
        initialization.setCurDecodePlugin(filePath + fileName);
        initialization.setCurDecodePluginClass(name);
        return new RestResult();
    }


    @ApiOperation(value = "服务创建", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/createService", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public RestResult createService(@RequestBody BootServerParameter bootServerParameter) {

        try {
            serverManage.createService(bootServerParameter);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResult("失败:" + e.getMessage(),"10011");
        }

        return new RestResult();

    }


    @ApiOperation(value = "启动服务", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/startServer", method = RequestMethod.POST)
    public RestResult startServer(@RequestParam("serviceId") String serviceId) {
        serverManage.startServer(serviceId);
        return new RestResult();
    }

    @ApiOperation(value = "停止服务", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/stopServer", method = RequestMethod.POST)
    public RestResult stopServer(@RequestParam("serviceId") String serviceId) {
        serverManage.stopServer(serviceId);
        return new RestResult();
    }


    @ApiOperation(value = "获取服务信息", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/getServers", method = RequestMethod.GET)
    public RestResult<List<IotNodeDO>> getServers() {
        RestResult<List<IotNodeDO>> restResult = new RestResult<>();
        try {
            ConcurrentHashMap<String, AbstractBootServer> cloudIotServers = serverManage.getCloudIotServers();
            ServerCache.clear();
            cloudIotServers.forEach((id, server) -> {
                BootServerParameter bootServerParameter = server.getBootServerParameter();
                if (bootServerParameter != null){
                    IotNodeDO serverDo = new IotNodeDO();
                    serverDo.setServiceId(bootServerParameter.getServiceId());
                    serverDo.setServerType(bootServerParameter.getServerType());
                    serverDo.setServerName(bootServerParameter.getServerName());
                    serverDo.setIsStatus(server.isStatus);
                    serverDo.setCreateDate(bootServerParameter.getDate());
                    ServerCache.add(serverDo);
                }
            });
            restResult.setData(new ArrayList<>(ServerCache.getServerMap().values()));
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResult("失败" + e.getMessage());
        }
        return restResult;

    }


}
