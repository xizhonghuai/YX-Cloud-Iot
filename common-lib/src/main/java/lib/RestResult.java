package lib;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName RestResult
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
public class RestResult<T> {

    @JSONField(ordinal = 1)
    private boolean result = true;
    @JSONField(ordinal = 3)
    private String msg = "success";
    @JSONField(ordinal = 2)
    private String resultCode = "200";
    @JSONField(ordinal = 4)
    private T data;

    public RestResult() {
    }

    public RestResult(String msg) {
        this.msg = msg;
    }

    public RestResult(T data) {
        this.data = data;
    }

    public RestResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public RestResult(String msg, String resultCode, T data) {
        this.msg = msg;
        this.resultCode = resultCode;
        this.data = data;
    }

    public RestResult(boolean result, String msg, String resultCode, T data) {
        this.result = result;
        this.msg = msg;
        this.resultCode = resultCode;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", data=" + data +
                '}';
    }
}
