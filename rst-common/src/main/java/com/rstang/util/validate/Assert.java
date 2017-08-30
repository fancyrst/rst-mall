package com.rstang.util.validate;

import java.util.Collection;
import java.util.Map;

/**
 * Assertion utility class that assists in validating arguments.
 * 
 * @author yexiong
 * @since 1.1
 */
public abstract class Assert {
	
	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * @param expression
	 * @param message
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	/**
	 * Assert that an object is not null.
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}	
	
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
	
	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void notEmpty(Object[] array, String message) {
		if (ValidateUtils.isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}


	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}
	
	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
	 */
	public static void notEmpty(Map map, String message) {
		if (ValidateUtils.isEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Map map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}
	
	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	public static void notEmpty(Collection collection, String message) {
		if (ValidateUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Collection collection) {
		notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}	
	
	/**
	 */
	public static void notEmpty(String string, String message) {
		if (ValidateUtils.isEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notEmpty(String string) {
		notEmpty("[Assertion failed] - this string must not be empty");
	}
	
//	public static void hasText(String text) {
//		hasText(text,
//				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
//	}
//	
//	public static void hasText(String text, String message) {
//		if (!StringUtils.hasText(text)) {
//			throw new IllegalArgumentException(message);
//		}
//	}
}
