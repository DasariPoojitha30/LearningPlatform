package com.infy.utility;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.exception.CourseException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(CourseException.class)
	public ResponseEntity<ErrorInfo> CourseServiceException(CourseException courseException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(courseException.getMessage());
		errorInfo.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> GeneralException(Exception exception){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(exception.getMessage());
		errorInfo.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
//	@ExceptionHandler({
//        MethodArgumentNotValidException.class,
//        ConstraintViolationException.class
//})
//	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
//
//	    String errorMsg;
//
//	    if (exception instanceof MethodArgumentNotValidException) {
//
//	        MethodArgumentNotValidException manvException =
//	                (MethodArgumentNotValidException) exception;
//
//	        errorMsg = manvException.getBindingResult()
//	                .getAllErrors()
//	                .stream()
//	                .map(ObjectError::getDefaultMessage)
//	                .collect(Collectors.joining(", "));
//
//	    } else {
//
//	        ConstraintViolationException cvException =
//	                (ConstraintViolationException) exception;
//
//	        errorMsg = cvException.getConstraintViolations()
//	                .stream()
//	                .map(ConstraintViolation::getMessage)
//	                .collect(Collectors.joining(", "));
//	    }
//
//	    ErrorInfo errorInfo = new ErrorInfo();
//
//	    errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
//	    errorInfo.setErrorMessage(errorMsg);
//	    errorInfo.setTimeStamp(LocalDateTime.now());
//
//	    return new ResponseEntity<>(
//	            errorInfo,
//	            HttpStatus.BAD_REQUEST
//	    );
//	}
//	
		
}
