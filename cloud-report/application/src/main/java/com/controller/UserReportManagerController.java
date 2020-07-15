package com.controller;

import com.alibaba.fastjson.JSON;
import com.model.UserReportDo;
import com.service.UserReportService;
import lib.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * @ClassName ReportManagerController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/6/18
 * @Version V1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("manager/")
public class UserReportManagerController {

    @Autowired
    private UserReportService userReportService;

    @RequestMapping("getUserReportInfo")
    public RestResult<List<UserReportDo>> get(@RequestParam(value = "id", required = false) Integer id,
                                              @RequestParam(value = "reportKey", required = false) String reportKey,
                                              @RequestParam(value = "reportName", required = false) String reportName) {

        HashMap<String, Object> parMap = new HashMap<>();
        parMap.put("id", id);
        parMap.put("reportKey", reportKey);
        parMap.put("reportName", reportName);
        List<UserReportDo> select = userReportService.select(parMap);
        return new RestResult<>(select);
    }


    @RequestMapping(value = "/userReport/add",method = RequestMethod.POST)
    public RestResult<Boolean> add(@RequestBody UserReportDo userReportDo) {
        userReportService.insert(userReportDo);
        return new RestResult<>(true);
    }

    @RequestMapping(value = "/userReport/edit",method = RequestMethod.POST)
    public RestResult<Boolean> edit(@RequestBody UserReportDo userReportDo) {
        HashMap<String, Object> parMap = JSON.parseObject(JSON.toJSONString(userReportDo),HashMap.class);
        userReportService.updateByPrimary(parMap);
        return new RestResult<>(true);
    }

    @RequestMapping(value = "/userReport/del",method = RequestMethod.POST)
    public RestResult<Boolean> del(@RequestParam("id") Integer id) {
        userReportService.deleteByPrimary(id);
        return new RestResult<>(true);
    }



}
