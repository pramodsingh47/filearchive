package com.filedm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.filedm.dto.ArchiveResponse;

@ControllerAdvice
public class GlobalExceptinHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(value = Exception.class)
	public ArchiveResponse exceptionGeneric(HttpMediaTypeNotSupportedException s)
	{
		return new ArchiveResponse(HttpStatus.BAD_REQUEST.value(), " Controller Adive file is not empty");
		
	}

}
