package com.rstang.util.concurrent;

import com.pasoft.core.exception.SystemException;

/**
 * 并发任务异常
 * 
 * Created by yeyx on 2017/01/29.
 */
public class ConcurrencyException extends SystemException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcurrencyException(String message) {
		super(message);
	}
	
	public ConcurrencyException(String message, Exception cause) {
	    super(message, cause);
	}
	
	public ConcurrencyException(Exception cause) {
        super(cause);
    }
}
