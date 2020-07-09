package com.wu.exception.handler;

import java.io.Serializable;

/**
 * This class holds the information of client error
 *
 * @author western union
 */
public class ClientErrorInformation implements Serializable {
	
	private static final long serialVersionUID = -9022212125024593367L;
	
	private String message;
	private String uriMessage;
	private String cause;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public ClientErrorInformation(String message, String uriMessage, String cause) {
		super();
		this.message = message;
		this.uriMessage = uriMessage;
		this.cause = cause;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUriMessage() {
		return uriMessage;
	}

	public void setUriMessage(String uriMessage) {
		this.uriMessage = uriMessage;
	}
	
}
