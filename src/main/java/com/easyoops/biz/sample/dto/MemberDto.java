package com.easyoops.biz.sample.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
@Builder
public class MemberDto {
    @ApiModelProperty(value = "id", required = true, example = "1000000001")
    private Integer id;
    @ApiModelProperty(value = "emailId", required = true, example = "admin@test.com")
    private String emailId;
    @ApiModelProperty(value = "memberName", required = true, example = "admin")
    private String memberName;
    @ApiModelProperty(value = "role", required = true, example = "SA")
    private String roleId;
}
