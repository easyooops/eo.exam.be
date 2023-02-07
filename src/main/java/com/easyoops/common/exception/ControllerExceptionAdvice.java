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

	@ExceptionHandler(RuntimeException.class)
	public ResponseDTO<String> handleRuntimeExceptionBase(RuntimeException exception) {
		LOG.error(exception.getMessage());
		return new ResponseDTO<>(ResponseCode.SERVER_ERROR, exception.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseDTO<String> handleIllegalArgument(IllegalArgumentException exception) {
		LOG.error(exception.getMessage());
		return new ResponseDTO<>(ResponseCode.PARAM_INVALID, exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseDTO<String> handleException(Exception exception) {
		LOG.error(exception.getMessage());
		return new ResponseDTO<>(ResponseCode.SERVER_ERROR, exception.getMessage());
	}
}
