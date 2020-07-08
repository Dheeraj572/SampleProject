package com.techmojo.exception;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private List<String> message;
	private HttpStatus status;
	private String timeStamp;
	
	public ErrorResponse(List<String> message) {
		super();
		this.message = message;
		this.status=HttpStatus.BAD_REQUEST;
		this.timeStamp = ZonedDateTime.now(ZoneOffset.UTC).toString();
	}
	
}
