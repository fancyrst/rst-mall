/**
 * Copyright 2013 The EIS Framework Team All rights reserved.
 * EIS Team members: Zhao haiyang, Ye yaoxiong
 */
package com.rstang.core.config;

import java.util.HashMap;
import java.util.Map;

import com.rstang.util.resource.PropertiesLoader;

/**
 * rstang global configuration
 * @author yexiong
 *
 */
public class GlobalConfig {
	
	/**
	 * 当前对象实例
	 */
	private static GlobalConfig global = new GlobalConfig();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> globalMap = new HashMap<String, String>();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("common-context.properties");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = globalMap.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			globalMap.put(key, value);
		}
		return value;
	}

	/**
	 * 获取当前对象实例
	 */
	public static GlobalConfig getInstance() {
		return global;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}

	/**
	 * 获取CKFinder上传文件的根目录
	 * @return
	 */
	public static String getCkBaseDir() {
		String dir = getConfig("userfiles.basedir");
//		Assert.hasText(dir, "配置文件里没有配置userfiles.basedir属性");
		if(!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}
	
	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
}
