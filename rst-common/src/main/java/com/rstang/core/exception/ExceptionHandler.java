/**
 * Copyright 2013 The EIS Framework Team All rights reserved.
 * EIS Team members: Zhao haiyang, Ye yaoxiong
 */
package com.rstang.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理类
 * 
 * @author Ye yaoxiong
 */
public class ExceptionHandler {
	
	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

	/**
	 * 在request中获取异常类
	 * @param request
	 * @return 
	 */
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("exception") != null) {
			ex = (Throwable) request.getAttribute("exception");
		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return ex;
	}
	
	public static String getExceptionStackTrace(Throwable e) {
		return getExceptionStackTrace(e, -1);
	}
	
	/**
	 * 根据长度获取错误堆栈信息，长度<=0时获取全量
	 * 
	 * @param e
	 * @param length
	 * @return
	 */
	public static String getExceptionStackTrace(Throwable e, int length) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			e.printStackTrace(pw);
			if (length <=0 ) {
				return sw.toString();
			} else {
				String stack = sw.toString();
				if (stack.length() > length) {
					return stack.substring(0, length);
				} else {
					return sw.toString();
				}
			}
		} finally {
			pw.close();
		}
	}
}
