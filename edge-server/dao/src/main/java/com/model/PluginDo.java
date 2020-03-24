package com.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName PluginDo
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Data
@ToString
public class PluginDo {

   private Integer id;
   private Integer userId;
   private String jarUrl;
   private String executeClass;
   private String jarName;
   private String description;
   private Date editDate;
   private String userName;



}
