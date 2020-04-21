package com;

import com.alibaba.fastjson.JSON;
import com.manage.ServerManage;
import com.transmission.server.core.BootServerParameter;
import com.transmission.server.core.ConnectProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CodeTest
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/16
 * @Version V1.0
 **/
public class CodeTest {





    public static void main(String[] args) {
        ConnectProperty connectProperty = new ConnectProperty();
        connectProperty.setServiceId("1234");
        connectProperty.setServiceId("ser");

        String[] arguments = new String[] {"python", "F:\\JAVA\\YX-Cloud-Iot\\pyscript\\decodeplugin\\com\\MyDeCodePlugin.py", "\"ww\"","ss"};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            if (re == 0){
                System.out.println("调用成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
