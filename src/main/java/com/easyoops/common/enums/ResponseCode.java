package com.easyoops.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
	OK("200", "success"),
	PARAM_INVALID("400", "Bad Request, parameter is invalid"),
	AUTH_INVALID("401", "Unauthorized, authorization header is invalid"),
	NO_ACCESS("403", "Forbidden, no access"),
	NOT_FOUND("404", "Not Found, does not exist"),
	TOO_MANY_REQUEST("429", "Too Many Requests"),
	SERVER_ERROR("500", "Internal Server Error");

	private final String resultCode;
	private final String resultMsg;

	ResponseCode(String code, String desc) {
		this.resultCode = code;
		this.resultMsg = desc;
	}
}
