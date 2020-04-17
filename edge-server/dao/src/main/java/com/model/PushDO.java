package com.model;

import lombok.Data;

/**
 * @ClassName PushDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/16
 * @Version V1.0
 **/

@Data
public class PushDO extends BaseModel {

    private String name;
    private String protocol;
    private String params;



}
