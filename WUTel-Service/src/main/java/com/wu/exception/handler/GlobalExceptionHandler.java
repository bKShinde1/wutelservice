package com.wu.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wu.exception.CustomerNotFoundException;
import com.wu.exception.RequestViolationException;

/*
*This exception handeler controller handle user generated exception
*/

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> handlerForCustomerNotFoundException(HttpServletRequest request,
			                                                                          Exception ex){
		
		return this.getResponseEntity(request,ex,HttpStatus.ACCEPTED);
		
	}
	
	@ExceptionHandler(RequestViolationException.class)
	public ResponseEntity<ClientErrorInformation> handlerForRequestViolationException(HttpServletRequest request,
			                                                                          Exception ex){
		return this.getResponseEntity(request,ex,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	private ClientErrorInformation getClientErrorInformation(HttpServletRequest request, Exception ex) {
        LOG.warn("Exception occurred while processing the request with Message- {}", ex.getLocalizedMessage(), ex);
        String causeMessage = ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
        return new ClientErrorInformation(ex.getLocalizedMessage(), request.getRequestURI(), causeMessage);
    }

    private ResponseEntity<ClientErrorInformation> getResponseEntity(HttpServletRequest request, Exception ex, HttpStatus status) {
        return new ResponseEntity<>(this.getClientErrorInformation(request, ex), status);
    }


}
