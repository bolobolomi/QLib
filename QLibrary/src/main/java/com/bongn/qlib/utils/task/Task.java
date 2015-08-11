/**   
 * @Title: Task.java 
 * @Package com.xqdok.taskmanager 
 * @author WQ
 * @date 2014-1-17 上午10:28:09 
 * @version V1.0   
 */
package com.bongn.qlib.utils.task;


import com.bongn.qlib.exception.HTTPException;

/**
 * @author WQ
 * mName task名字
 * unInNewThread 是否在新的线程执行，即非UI线程
 * 
 */
public abstract class Task {
	private int mId = 0;
	public String mName;
	public boolean mRunInBackThread;
	
	/**
	 * @author WQ
	 * @param mName task名字
	 * @param runInNewThread 是否在新的线程执行，即非UI线程
	 * 
	 */
	public Task(String mName, boolean runInNewThread) {
		super();
		this.mName = mName;
		this.mRunInBackThread = runInNewThread;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public boolean ismRunInBackThread() {
		return mRunInBackThread;
	}

	public void setmRunInBackThread(boolean mRunInBackThread) {
		this.mRunInBackThread = mRunInBackThread;
	}

	/**
	* @Description 需要在task内运行的任务
	* @Title onExecute
	* @author WQ
	* @date 2014-5-16 下午6:06:30
	* @param param
	* @return
	* @throws Exception
	 */
	public abstract TaskParm onExecute(TaskParm param) throws Exception;

	/**
	* @Description task取消时候的回调
	* @Title onCancle
	* @author WQ
	* @date 2014-5-16 下午6:06:50
	 */
	public void onCancle(){}
	
	/**
	* @Description Exception时的回调。一个exception，只能回调到onHttpError 或者 onError
	* @Title onError
	* @author WQ
	* @date 2014-5-16 下午6:07:14
	* @param e
	 */
	public void onError(Exception e){}
	
	/**
	* @Description HttpException时的回调。一个exception，只能回调到onHttpError 或者 onError
	* @Title onHttpError
	* @author WQ
	* @date 2014-5-16 下午6:07:29
	* @param e
	 */
	public void onHttpError(HTTPException e){}
	
	/**
	* @Description 暂时没用，预留以后使用
	* @Title onProgressUpdate
	* @author WQ
	* @date 2014-5-16 下午6:09:13
	* @param progresses
	 */
	public void onProgressUpdate(Object... progresses){}
}
