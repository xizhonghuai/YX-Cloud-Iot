package lib;

/**
 * @ClassName RestResult
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
public class RestResult<T> {

    private boolean result = true;
    private String msg = "success";
    private String resultCode = "200";
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
