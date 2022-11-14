package com.bupt.microboot.dto;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;


/**
 * 客户信息查询
 */
@Component
public class UserInfoQueryParam {

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfoQueryParam{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
