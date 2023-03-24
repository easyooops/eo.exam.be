package com.easyoops.biz.exam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
@Builder
public class AnswerDto {
    @ApiModelProperty(value = "ans_cont", required = false, example = "Create a secure perimeter using the Access Context Manager feature of VPC Service Controls and restrict access to the source IP range of the allowed clients and Google health check IP ranges")
    private String ans_cont;
    @ApiModelProperty(value = "ans_hit_yn", required = false, example = "N")
    private String ans_hit_yn;
}
