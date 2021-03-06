package com.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @ClassName UserDO
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Data
public class UserDO  extends BaseModel{
    private String username;
    @JSONField(serialize=false)
    private String password;
    private String tel;

}
