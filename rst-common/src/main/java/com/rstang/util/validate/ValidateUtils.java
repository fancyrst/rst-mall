/*
 * Copyright 2013 The EPT Team 
 * 
 * Project : EPT Platform, base on JDK1.7
 */
package com.rstang.util.validate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * EPT Platform validate util class
 * 
 * @author yexiong
 * @since 1.0
 */
public abstract class ValidateUtils {
	
	/**
	 * 判断字符串是否为空或null
	 * ValidateUtil.isEmpty(null) = true
	 * ValidateUtil.isEmpty("") = true
	 * ValidateUtil.isEmpty(" ") = false
	 * ValidateUtil.isEmpty("Hello") = false
	 * @param ip_str
	 * @return
	 */
	public static boolean isEmpty(String ip_str) {
		return null == ip_str || "".equals(ip_str);
	}
	
	/**
	 * 判断实现java.util.List接口的集合是否为空或null
	 * @param ip_list
	 * @return true:为空或null; false:不为null有值的集合
	 */
	public static <T> boolean isEmpty(List<T> ip_list) {
		return null == ip_list || ip_list.size() == 0;
	}
	
	/**
	 * 判断数组是否为空或null
	 * @param ip_arr
	 * @return  true:为空或null; false:不为null有值的数组
	 */
	public static boolean isEmpty(Object[] ip_arr) {
		return null == ip_arr || ip_arr.length == 0;
	} 
	
	
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}
	
	/**
	 * Return <code>true</code> if the supplied Map is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param map the Map to check
	 * @return whether the given Map is empty
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}
	
	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a CharSequence that purely consists of whitespace.
	 * <p><pre>
	 * ValidateUtils.hasLength(null) = false
	 * ValidateUtils.hasLength("") = false
	 * ValidateUtils.hasLength(" ") = true
	 * ValidateUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * ValidateUtils.hasText(null) = false
	 * ValidateUtils.hasText("") = false
	 * ValidateUtils.hasText(" ") = false
	 * ValidateUtils.hasText("12345") = true
	 * ValidateUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 * its length is greater than 0, and it does not contain whitespace only
	 * @see Character#isWhitespace
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}
}
