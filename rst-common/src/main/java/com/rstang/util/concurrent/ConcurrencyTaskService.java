package com.rstang.util.concurrent;

/**
 * 并发执行多任务接口
 * 
 * @author Ye yaoxiong
 */
public interface ConcurrencyTaskService {

	/**
	 * 执行批量任务
	 * @param batchTask
	 * @throws ConcurrencyException
	 */
	public void doBatchTask(final BatchTaskDTO batchTask) throws ConcurrencyException;
}
