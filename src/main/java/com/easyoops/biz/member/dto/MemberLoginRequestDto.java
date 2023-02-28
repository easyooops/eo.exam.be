package com.easyoops.biz.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberLoginRequestDto {
    @ApiModelProperty(value = "emailId", required = true, example = "admin@test.com")
    private String emailId;
    @ApiModelProperty(value = "password", required = true, example = "1234")
    private String password;

}
