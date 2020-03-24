package com.controller;

import com.init.Initialization;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName DocController
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/24
 * @Version V1.0
 **/
@Slf4j
@RestController
@RequestMapping("/api/doc/")
public class DocController {


    @Autowired
    private Initialization initialization;

    @ApiOperation(notes = "复制链接在浏览器中打开下载", value = "下载SDK", httpMethod = "GET",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @RequestMapping(value = "/download/sdk", method = RequestMethod.GET)
    public String downloadSdkFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "/doc/sdk.rar";//
         try {
            File file = new File(initialization.getResourcePath()+fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream");//
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e){

             log.error(e.getMessage());
         }
        return null;
    }






}
