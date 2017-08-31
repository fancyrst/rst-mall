package com.rstang.util.reflect;

import org.apache.commons.beanutils.BeanUtils;

public abstract class BeanHelper {

	public static <T> T getBean(Object orig, Class<T> clazz) throws Exception {
		try {
			T t = clazz.newInstance();
			BeanUtils.copyProperties(t, orig);
			return t;
		} catch (Exception e) {
			throw e;
		}
	}
}
