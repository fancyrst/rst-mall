/**
 * Copyright 2013 The EIS Framework Team All rights reserved.
 * EIS Team members: Zhao haiyang, Ye yaoxiong
 */
package com.rstang.core.exception;

/**
 * 平台初始化时发生异常
 * @author Yexiong
 *
 */
public class InitPlatformException extends SystemException {

	private static final long serialVersionUID = -5610131239183729374L;
	
	public InitPlatformException(String message) {
		super(message);
	}

	public InitPlatformException(String message, Exception cause) {
		super(message, cause);
	}

}
