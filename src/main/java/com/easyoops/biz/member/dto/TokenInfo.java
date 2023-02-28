package com.easyoops.biz.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

    @ApiModelProperty(value = "grantType", example = "Bearer")
    private String grantType;
    @ApiModelProperty(value = "accessToken", example = "eyJ∙∙.eyJz∙∙._U4Bhy∙∙")
    private String accessToken;
    @ApiModelProperty(value = "refreshToken", example = "eyJ∙∙.eyJl∙∙.WZROme∙∙")
    private String refreshToken;
}
