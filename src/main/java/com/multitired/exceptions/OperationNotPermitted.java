package com.multitired.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OperationNotPermitted  extends RuntimeException {
	static final long serialVersionUID = 43L;
}
