package com.easyoops.common.dto;

import com.easyoops.common.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp = new Date();

    @ApiModelProperty(value = "resultCode", required = true, example = "200")
    private String resultCode;
    @ApiModelProperty(value = "resultMsg", required = true, example = "success")
    private String resultMsg;

    private T resultData;

    public ResponseDTO(ResponseCode responseCode) {
        init(responseCode);
    }

    public ResponseDTO(ResponseCode responseCode, T resultData) {
        init(responseCode);
        this.resultData = resultData;
    }

    private void init(ResponseCode responseCode) {
        this.resultCode = responseCode.getResultCode();
        this.resultMsg = responseCode.getResultMsg();
    }
}
