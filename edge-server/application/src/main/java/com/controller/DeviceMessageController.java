package com.controller;

import com.cache.DeviceShadow;
import com.model.DeviceMsgDO;
import com.service.DeviceMsgService;
import com.toolutils.ConstantUtils;
import io.swagger.annotations.*;
import lib.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.omg.Dynamic.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName DeviceMessageController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/25
 * @Version V1.0
 **/
@Slf4j
@RestController
@RequestMapping("/api/device/message/")
public class DeviceMessageController {

    @Autowired
    private DeviceMsgService deviceMsgService;


    @ApiOperation(value = "查询最新数据", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "path", name = "deviceId", dataType = "String", required = true, value = "设备Id"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/{serviceId}/{deviceId}", method = RequestMethod.GET)
    public RestResult<Object> getMessage(@PathVariable("serviceId") String serviceId,@PathVariable("deviceId") String deviceId){
       return new RestResult<>(DeviceShadow.shadow.get(serviceId+deviceId));
    }


    @ApiOperation(value = "查询历史数据", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "serviceId", dataType = "String", required = true, value = "服务id"),
            @ApiImplicitParam(paramType = "path", name = "deviceId", dataType = "String", required = true, value = "设备Id"),
            @ApiImplicitParam(paramType = "query", name = "beginDate", dataType = "Date", required = false, value = "开始时间"),
            @ApiImplicitParam(paramType = "query", name = "endDate", dataType = "Date", required = false, value = "结束时间"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/ls/{serviceId}/{deviceId}", method = RequestMethod.GET)
    public RestResult<Object> getMessage(
            @PathVariable("serviceId") String serviceId,
            @PathVariable("deviceId") String deviceId,
            @RequestParam(value = "beginDate",required = false) Date beginDate,
            @RequestParam(value = "endDate",required = false) Date endDate
    ){

        HashMap<String ,Object> par = new HashMap<>();
        par.put(ConstantUtils.SERVICE_ID,serviceId);
        par.put(ConstantUtils.DEVICE_ID,deviceId);
        if (beginDate != null && endDate != null){
            par.put("beginDate",beginDate);
            par.put("endDate",endDate);
        }
        try {
            List<DeviceMsgDO> datas = deviceMsgService.select(par);
            return new RestResult<>(datas);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResult("失败"+e.getMessage());
        }

    }







}
