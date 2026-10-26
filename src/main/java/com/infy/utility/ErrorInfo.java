package com.infy.utility;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorInfo {

	private String errorMessage;
	private LocalDateTime timeStamp;
	private Integer errorCode;

}
