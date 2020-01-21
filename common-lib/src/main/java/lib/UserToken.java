package lib;

/**
 * @ClassName UserToken
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/

public class UserToken {

    private String userName;
    private String authCode;

    public UserToken(String userName, String authCode) {
        this.userName = userName;
        this.authCode = authCode;
    }

    public UserToken() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userName='" + userName + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
