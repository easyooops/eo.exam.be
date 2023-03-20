package com.easyoops.biz.exam.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
@Valid
@Builder
public class ExamDto {
    @ApiModelProperty(value = "exam_no", required = true, example = "1000000001")
    private Integer exam_no;

}
