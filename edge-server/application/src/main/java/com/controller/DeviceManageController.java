package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.Context;
import com.manage.DeviceManage;
import com.model.DeviceDO;
import com.model.UserDO;
import com.service.DeviceService;
import io.swagger.annotations.*;
import lib.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@CrossOrigin
@RestController
@RequestMapping("/api/device/manage/")
public class DeviceManageController {

    @Autowired
    private DeviceManage deviceManage;

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "添加设备", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "DeviceDO", dataType = "Object", required = true, value = "数据"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RestResult addDevice(@RequestBody DeviceDO deviceDO) {
        try {
            deviceService.insert(deviceDO);
            return new RestResult();
        } catch (Exception e) {
            return new RestResult("err:" + e.getMessage(), "10001");
        }
    }


    @ApiOperation(value = "修改设备", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "DeviceDO", dataType = "Object", required = true, value = "数据"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResult editDevice(@RequestBody DeviceDO deviceDO) {
        try {
//            if (deviceDO.getId()== null){
//                new Exception("id is null");
//            }
            deviceService.updateByPrimary(JSONObject.parseObject(JSONObject.toJSONString(deviceDO),HashMap.class));
            return new RestResult();
        } catch (Exception e) {
            return new RestResult("err:" + e.getMessage(), "10001");
        }
    }



    @ApiOperation(value = "查询设备", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "deviceId", dataType = "String", value = "deviceId"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public RestResult<List<DeviceDO>> getDevice(@RequestParam(value = "deviceId",required = false) String deviceId) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            if (deviceId !=null){
                map.put("deviceId",deviceId);
            }
            return new RestResult(deviceService.select(map));
        } catch (Exception e) {
            return new RestResult("err:" + e.getMessage(), "10001");
        }
    }




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
    public RestResult sendCmd(@RequestParam("serviceId") String serviceId, @RequestBody Object cmd) {
        deviceManage.sendCmd(serviceId, cmd);
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
    public RestResult sendCmd(@RequestParam("serviceId") String serviceId, @RequestParam("regId") String regId, @RequestBody Object cmd) {
        deviceManage.sendCmd(serviceId, regId, cmd);
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
    public RestResult disconnect(@RequestParam("serviceId") String serviceId, @RequestParam("regId") String regId) {
        deviceManage.disconnect(serviceId, regId);
        return new RestResult();
    }


    @ApiOperation(value = "获取已链接设备", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "serviceId", dataType = "String", value = "服务id"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/getOnline", method = RequestMethod.GET)
    public RestResult<List> getOnline(@RequestParam(value = "serviceId",required = false) String serviceId) {
        if (serviceId != null){
            return new RestResult(deviceManage.getOnlineDeviceList(serviceId));
        }
        return new RestResult(deviceManage.getOnlineDeviceList());
    }


}
