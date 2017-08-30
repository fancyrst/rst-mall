package com.rstang.util.concurrent;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 批量任务DTO
 * 
 * Created by yeyx on 2017/01/29.
 */
public class BatchTaskDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, AbstractBaseTask> tasks = new ConcurrentHashMap<String, AbstractBaseTask>();
	
	/**
	 * 批量任务执行等待时间，单位：毫秒
	 */
	private long timeout = 10*1000;
}
