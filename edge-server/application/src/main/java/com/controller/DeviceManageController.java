package com.controller;

import com.manage.DeviceManage;
import io.swagger.annotations.*;
import lib.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName DeviceManageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
@Slf4j
@RestController
@RequestMapping("/api/device/manage/")
public class DeviceManageController {

    @Autowired
    private DeviceManage deviceManage;

    @ApiOperation(value = "指令发送,广播方式", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "body", name = "cmd", dataType = "Object", required = true, value = "数据"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/sendcmd", method = RequestMethod.POST)
    public RestResult sendCmd(@RequestParam("serviceId") String serviceId, @RequestBody Object cmd){
        deviceManage.sendCmd(serviceId,cmd);
        return new RestResult();
    }



    @ApiOperation(value = "指令发送", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "query", name = "regId", dataType = "String", required = true, value = "设备id"),
            @ApiImplicitParam(paramType = "body", name = "cmd", dataType = "Object", required = true, value = "数据"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/sendcmdbydevice", method = RequestMethod.POST)
    public RestResult sendCmd(@RequestParam("serviceId") String serviceId,@RequestParam("regId") String regId,@RequestBody Object cmd){
        deviceManage.sendCmd(serviceId,regId,cmd);
        return new RestResult();
    }

    @ApiOperation(value = "断开某设备", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "query", name = "regId", dataType = "String", required = true, value = "设备id"),

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public RestResult disconnect(@RequestParam("serviceId") String serviceId,@RequestParam("regId") String regId){
        deviceManage.disconnect(serviceId,regId);
        return new RestResult();
    }



    @ApiOperation(value = "获取已链接设备", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", required = true, value = "服务id"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/getdevices", method = RequestMethod.GET)
    public RestResult<List<HashMap<String,Object>>> getDevices(@RequestParam("serviceId")String serviceId){
        return new RestResult(deviceManage.getDevices(serviceId));
    }





}
