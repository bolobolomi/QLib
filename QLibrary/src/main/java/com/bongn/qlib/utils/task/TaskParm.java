/**   
 * @Title: TaskParm.java 
 * @Package com.xqdok.taskmanager 
 * @author WQ
 * @date 2014-1-17 上午10:39:27 
 * @version V1.0   
 */
package com.bongn.qlib.utils.task;

import java.util.ArrayList;

/**
 * @ClassName: TaskParm
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author WQ
 * @date 2014-5-16 上午10:39:27
 * 
 */
public class TaskParm {
	private Object[] mTaskParams = null;

	public TaskParm(Object[] nextTaskParams) {
		mTaskParams = nextTaskParams;
	}

	public TaskParm(TaskParm operation) {
		setTaskParams(operation);
	}

	public Object[] getTaskParams() {
		return mTaskParams;
	}

	public void setTaskParams(Object[] mTaskParams) {
		this.mTaskParams = mTaskParams;
	}

	public void setTaskParams(TaskParm operation) {
		if (operation == this) {
			throw new IllegalArgumentException("The argument can NOT be self.");
		}

		if (null == operation) {
			return;
		}

		Object[] params = operation.getTaskParams();
		if (null == params) {
			return;
		}

		ArrayList<Object> paramsList = new ArrayList<Object>();

		if (null != mTaskParams) {
			for (Object param : mTaskParams) {
				paramsList.add(param);
			}
		}

		for (Object param : params) {
			paramsList.add(param);
		}

		mTaskParams = paramsList.toArray();
	}

}
