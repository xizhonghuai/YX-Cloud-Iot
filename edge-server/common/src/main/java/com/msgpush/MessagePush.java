package com.msgpush;

import java.util.HashMap;

/**
 * @ClassName MessagePush
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
public abstract class MessagePush {
    public HashMap<String,Object> args = new HashMap<>();
    public abstract void init();
    public abstract void push(Object message);
    public abstract void close();
}
