package client.http;

/**
 * @ClassName TMHttpCallbackPrams
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/2
 * @Version V1.0
 **/
public class TMHttpCallbackPrams {

    private String responseBody;

    private int statusCode;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



}
