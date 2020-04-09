package com.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName RuleEngineDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Data
@ToString
public class RuleEngineDO extends BaseModel{

   private String ruleEngineName;
   private String url;
   private String executeClass;





}
