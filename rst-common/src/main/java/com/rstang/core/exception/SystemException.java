package com.rstang.core.exception;

public abstract class SystemException extends Exception implements PlatformException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7545429340521632340L;

	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(String message, Exception cause) {
	    super(message, cause);
	}
	
	public SystemException(Exception cause) {
        super(cause);
    }
}
