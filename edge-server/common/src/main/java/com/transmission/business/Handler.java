package com.transmission.business;

import com.transmission.business.BusinessHandler;
import org.apache.mina.core.service.IoHandlerAdapter;

/**
 * @ClassName Handler
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class Handler extends IoHandlerAdapter {

    public BusinessHandler businessHandler;



    public Handler(BusinessHandler businessHandler) {
        this.businessHandler = businessHandler;
    }
}
