package com.msgpush;

/**
 * @ClassName MessagePush
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
public interface MessagePush {
    void init();
    void push(Object message);
    void close();
}
