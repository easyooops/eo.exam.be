package com.easyoops.common.dto;

import com.easyoops.common.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Integer code;

    private String codeName;

    private String desc;

    private T resultData;

    public ResponseDTO(ResponseCode responseCode) {
        init(responseCode);
    }

    public ResponseDTO(ResponseCode responseCode, T resultData) {
        init(responseCode);
        this.resultData = resultData;
    }

    private void init(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.codeName = responseCode.name();
        this.desc = responseCode.getDescription();
    }
}
