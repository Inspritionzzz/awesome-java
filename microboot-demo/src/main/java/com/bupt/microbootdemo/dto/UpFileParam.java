package com.bupt.microbootdemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 文件上传参数
 * Created by jason on 2012/9/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpFileParam {
    @NotEmpty
    @ApiModelProperty(value = "文件名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "文件所属用户名", required = true)
    private String userId;
}
