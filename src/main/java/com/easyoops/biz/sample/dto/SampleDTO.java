package com.easyoops.biz.sample.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Valid
public class SampleDTO {

    @ApiModelProperty(value = "no", required = true, example = "1")
    private String no;

    @ApiModelProperty(value = "title", required = true, example = "title is ...")
    private String title;

    @ApiModelProperty(value = "contents", required = false, example = "contents is ...")
    private String contents;

    @ApiModelProperty(hidden = true)
    private String createId;
    @ApiModelProperty(hidden = true)
    private Date createDt;
    @ApiModelProperty(hidden = true)
    private String updateId;
    @ApiModelProperty(hidden = true)
    private Date updateDt;

}
