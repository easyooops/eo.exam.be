package com.easyoops.biz.exam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@Valid
@Builder
public class ExamRequestDto {

    @ApiModelProperty(value = "pub_exam_no", required = true, example = "1000000001")
    private String pub_exam_no;
    @ApiModelProperty(value = "exam_open_yn", required = false, example = "Y")
    private String exam_open_yn;
    @ApiModelProperty(value = "exam_reg_tp", required = false, example = "1")
    private String exam_reg_tp;
    @ApiModelProperty(value = "exam_rnd_yn", required = false, example = "Y")
    private String exam_rnd_yn;
    @ApiModelProperty(value = "dump_file", required = false, example = "")
    private String dump_file;
    @ApiModelProperty(value = "qst_list", required = false, example = "")
    private List<QuestionDto> qst_list;
}
