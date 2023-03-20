package com.easyoops.biz.exam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
@Builder
public class AnswerDto {
    @ApiModelProperty(value = "ans_cont", required = false, example = "1")
    private Integer qst_no;
    @ApiModelProperty(value = "ans_hit_yn", required = false, example = "N")
    private String ans_hit_yn;
}
