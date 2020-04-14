package com.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName BuHandlerDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Data
@ToString
public class BuHandlerDO extends BaseModel{

   private String name;
   private String fileName;
   private String url;
   private String executeClass;





}
