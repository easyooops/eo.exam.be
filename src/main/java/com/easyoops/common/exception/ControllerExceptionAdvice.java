package com.easyoops.common.exception;

import com.easyoops.common.config.DataSourceConfig;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

//	@ResponseStatus(HttpStatus.OK)
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseDTO<String> handleRuntimeExceptionBase(WebRequest request, RuntimeException exception) {
//
//		LOG.error(exception.getMessage());
//		return new ResponseDTO<>(ResponseCode.SERVER_ERROR, exception.getMessage());
//	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseDTO<String> handleException(WebRequest request, Exception exception) {
		//exception.printStackTrace();
		LOG.error(exception.getMessage());
		return new ResponseDTO<>(ResponseCode.SERVER_ERROR, exception.getMessage());
	}
}
