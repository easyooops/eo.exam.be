package com.easyoops.biz.comn.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
public class ComnCodeDto {

    private String comnGrpCd;
    private String comnCd;
    private String comnGrpNm;
    private String comnNm;
    private String comnOrd;
    private String comnDesc;

}
