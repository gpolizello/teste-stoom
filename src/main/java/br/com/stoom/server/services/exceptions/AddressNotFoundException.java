package br.com.stoom.server.services.exceptions;

public class AddressNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8197472785935080521L;

	public AddressNotFoundException(String message) {
		super(message);
	}
	
	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
