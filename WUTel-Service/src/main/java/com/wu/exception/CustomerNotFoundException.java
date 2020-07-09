package com.wu.exception;

public class CustomerNotFoundException extends Exception {
	
	private static final long serialVersionUID = -1082962358188776783L;

	public CustomerNotFoundException() {

		super();
		
	}
	
	public CustomerNotFoundException(String message) {
		
        super(message);
        
	}
	
}
