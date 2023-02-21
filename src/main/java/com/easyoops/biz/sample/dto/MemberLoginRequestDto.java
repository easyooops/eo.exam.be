package com.easyoops.biz.sample.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String emailId;
    private String password;

}
