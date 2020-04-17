package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.model.PushDO;
import com.service.PushService;
import io.swagger.annotations.*;
import lib.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName PushController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/16
 * @Version V1.0
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/push/manage/")
public class PushManageController {

    @Autowired
    private PushService pushService;


    @ApiOperation(value = "添加转发器", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "PushDO", dataType = "Object", required = true, value = "数据"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RestResult add(@RequestBody PushDO pushDO) {
        try {
            if (!StringUtils.isEmpty(pushDO.getName())) {
                HashMap<String, Object> map = JSON.parseObject(pushDO.getParams(), new TypeReference<HashMap<String, Object>>() {
                });
                pushService.insert(pushDO);
            }
        } catch (Exception e) {
            return new RestResult("err:" + e.getMessage(), "10001");
        }
        return new RestResult();
    }


    @ApiOperation(value = "获取转发信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Integer", value = "id"),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", value = "名称"),
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public RestResult<List<PushDO>> get(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "id", required = false) Integer id) {

        HashMap<String, Object> map = new HashMap<>();
        if (name != null) {
            map.put("name", name);
        }
        if (id != null) {
            map.put("id", id);
        }
        return new RestResult<>(pushService.select(map));
    }


    @ApiOperation(value = "删除转发信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Integer", value = "id"),
    })
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public RestResult del(@RequestParam(value = "id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        try {
            pushService.deleteByPrimary(map);
        } catch (Exception e) {
            new RestResult<>("err:" + e.getMessage(), "10001");
        }
        return new RestResult<>();
    }


}
