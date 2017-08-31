/**
 * Copyright 2013 The EIS Framework Team All rights reserved.
 * EIS Team members: Zhao haiyang, Ye yaoxiong
 */
package com.rstang.core.exception;

/**
 * 数据访问异常基类
 * 
 * @author yexiong
 *
 */
public abstract class DAOException extends SystemException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2530613013823531821L;

	public DAOException(String message, Exception cause) {
		super(message, cause);
	}
}
