package com.techmojo.globalexceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techmojo.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleUserValidationException(Exception ex) {
		List<String> errorList = new ArrayList<>();
		errorList.add(ex.getMessage());
		return new ErrorResponse(errorList);
	}
}
