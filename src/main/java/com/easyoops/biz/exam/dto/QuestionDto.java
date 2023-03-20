package com.easyoops.biz.exam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@Valid
@Builder
public class QuestionDto {
    @ApiModelProperty(value = "qst_no", required = false, example = "1")
    private Integer qst_no;
    @ApiModelProperty(value = "qst_cont", required = false, example = "You need to restrict access to your Google Cloud load-balanced application so that only specific IP addresses can connect. What should you do?")
    private String qst_cont;
    @ApiModelProperty(value = "ans_list", required = false, example = "")
    private List<AnswerDto> ans_list;
}
