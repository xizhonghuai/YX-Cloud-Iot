package com.controller;

import com.common.Context;
import com.init.Initialization;
import com.model.DeCodePluginDO;
import com.model.UserDO;
import com.service.DeCodePluginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lib.FileUntis;
import lib.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName DeCodePluginController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/deccodeplugin/manage/")
public class DeCodePluginController {

    @Autowired
    private DeCodePluginService deCodePluginService;

    @Autowired
    private Initialization initialization;




    @ApiOperation(value = "上传数据解码插件", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "名称"),
            @ApiImplicitParam(paramType = "query", name = "executeClass", dataType = "String", required = true, value = "主类入口"),
            @ApiImplicitParam(paramType = "query", name = "description", dataType = "String", required = true, value = "描述"),
    })
    @RequestMapping(value = "/pluginUpload", method = RequestMethod.POST)
    public RestResult pluginUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("name") String name,
                                   @RequestParam("executeClass") String executeClass,
                                   @RequestParam("description") String description) {
        if (file.isEmpty()) {
            return new RestResult("file is null","11011");
        }

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(executeClass)) {
            return new RestResult("params is null","11011");
        }

        String fileName = file.getOriginalFilename();
        UserDO userDO = (UserDO)Context.getInstance().getObj();
        String fileUrl = initialization.getDecodePluginBasePath() + "/" + userDO.getUsername() + "/"+fileName;
        File dest = new File(fileUrl);
        FileUntis.createParentPath(dest);
        try {
            file.transferTo(dest);
            DeCodePluginDO deCodePluginDO = new DeCodePluginDO();
            deCodePluginDO.setName(name);
            deCodePluginDO.setFileName(fileName);
            deCodePluginDO.setUrl(fileUrl);
            deCodePluginDO.setDescription(description);
            deCodePluginDO.setExecuteClass(executeClass);
            deCodePluginDO.setCreateDate(new Date());
            deCodePluginService.insert(deCodePluginDO);
            log.info(fileName + "-----------上传成功");
        } catch (IOException e) {
            log.error(e.toString(), e);
            return new RestResult("上传失败" + e.getMessage());
        }
//        initialization.setCurDecodePlugin(filePath + fileName);
//        initialization.setCurDecodePluginClass(name);
        return new RestResult();

    }


    @ApiOperation(value = "获取插件信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Integer", value = "解码id"),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", value = "解码插件类名称"),
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public RestResult<List<DeCodePluginDO>> get(@RequestParam(value = "name",required = false) String name,
                                                @RequestParam(value = "id",required = false) Integer id){

        HashMap<String,Object> map = new HashMap<>();
        if (name != null){
            map.put("name",name);
        }
        if (id != null){
            map.put("id",id);
        }
        return new RestResult<>(deCodePluginService.select(map));
    }







}
