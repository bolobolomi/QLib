/**   
 * @Title: TaskManager.java 
 * @Package com.xqdok.taskmanager 
 * @author WQ
 * @date 2014-1-17 上午10:49:26 
 * @version V1.0   
 */
package com.bongn.qlib.utils.task;

import java.util.ArrayList;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.bongn.qlib.exception.HTTPException;

/**
 * 多任务队列执行，用法：
 * <pre>
 new TaskManager(TestActivity.this)
  .ignoreException(true) //忽略错误(一项任务错误，后面任务是否继续)
  .showDialog("请稍等...") //Dialog内容，不设置内容将不显示Dialog
  .cancleOnBackPress(true) //是否点击Back取消Dialog（仅当showDialog有值时起作用）
  //调用next添加一个task 。
  .next(new Task("getNetwork", true) { //true 表示在新的线程执行
    \@Override
    public TaskParm onExecute(TaskParm param) throws Exception {
      Presence p = WebService.getUserStatus(mContext, 1);
      //实例化TaskParam放入 p ，此TaskParam可供下一个Task使用。
      param = new TaskParm(new Object[] { p });
      return param;
    }
    \@Override
    public void onHttpError(HTTPException e) {
      //System.out.println(e.getResponseCode());
      super.onHttpError(e);
    }
    \@Override
    public void onError(Exception e) {
      super.onError(e);
    }
    \@Override
    public void onCancle() {
      //System.out.println("取消任务执行");
      super.onCancle();
    }
  }).next(new Task("showData",false) {
    
    \@Override
    public TaskParm onExecute(TaskParm param) throws Exception {
      if(null != param)
      	System.out.println((Presence)param.getTaskParams()[0]);
      return param;
    }
  }).execute();
 * </pre>
 */
public class TaskManager {

	private static final int MSG_DOTASK = 0;
	private static final int MSG_DONETASK = 1;

	private ArrayList<Task> mTaskList;
	private Task mCurTask;
	private TaskParm mParam;
	private Context mContext;
	private String mDialogMsg;
	private ProgressDialog mProgressDialog;
	private boolean cancleable = false;
	private Handler mHandler;
	/**是否在执行某一任务是发生错误*/
	private boolean exceptionOccurred = false;
	/**是否忽略错误 ，当某一任务发生错误时，是否允许其后任务执行 ， 默认忽略 **/
	private boolean ignoreException = true;
	/**
	* @Description 展示Dialog
	* @Title showDialog
	* @author WQ
	* @date 2014-5-16 下午3:43:39
	* @param msg
	* @return
	 */
	public TaskManager showDialog(String msg){
		mDialogMsg = msg;
		return this;
	}
	/**
	* @Description 按下返回键取消任务
	* @Title cancleOnBackPress
	* @author WQ
	* @date 2014-5-16 下午3:44:02
	* @param cancle
	* @return
	 */
	public TaskManager cancleOnBackPress(boolean cancle){
		cancleable = cancle;
		return this;
	}
	
	/**
	* @Description 是否忽略错误 ，当某一任务发生错误时，是否允许其后任务执行 ， 默认忽略
	* @Title ignoreException
	* @author WQ
	* @date 2014-7-10 上午10:32:21
	* @param ignore
	* @return
	 */
	public TaskManager ignoreException(boolean ignore){
		ignoreException = ignore;
		return this;
	}
	
	public TaskManager(Context context) {
		
		mContext = context;
		
		if (null == mTaskList) {
			mTaskList = new ArrayList<Task>();
		}
		if (null == mHandler) {
			mHandler = new Handler() {
				/*
				 * @see android.os.Handler#handleMessage(android.os.Message)
				 */
				@Override
				public void handleMessage(Message msg) {
					if (MSG_DOTASK == msg.what) {
						HandlerThread hr = new HandlerThread(
								mCurTask.getmName()) {

							@Override
							public void run() {
								try {
									mParam = mCurTask.onExecute(mParam);
								} catch (Exception e) {
									if(e instanceof HTTPException){
										mCurTask.onHttpError((HTTPException)e);
									}else{
										mCurTask.onError(e);
									}
									exceptionOccurred = true;
								}
								
								mHandler.obtainMessage(MSG_DONETASK).sendToTarget();
								super.run();
							}

						};
						hr.start();
					}
					if (MSG_DONETASK == msg.what) {
						getNextTask();
					}
					super.handleMessage(msg);
				}
			};
		}
	}

	public TaskManager next(Task task) {
		if (null != task) {
			synchronized (mTaskList) {
				int id = mTaskList.size() + 1;
				task.setmId(id);
				mTaskList.add(task);
			}
		} else {
			throw new NullPointerException("task is null");
		}

		return this;
	}

	private void getNextTask() {
		if(exceptionOccurred && !ignoreException){
			//若发生了错误，并且不允许其后任务执行
			cancleAllTask();
			return;
		}
		
		synchronized (mTaskList) {
			if (mTaskList.size() <= 0) {
				return;
			}
			mCurTask = mTaskList.get(0);
			mTaskList.remove(0);
		}
		if (!mCurTask.ismRunInBackThread()) {
			try {
				mParam = mCurTask.onExecute(mParam);
			} catch (Exception e) {
				if(e instanceof HTTPException){
					mCurTask.onHttpError((HTTPException)e);
				}else{
					mCurTask.onError(e);
				}
				e.printStackTrace();
			}
			getNextTask();
		} else {
			mHandler.obtainMessage(MSG_DOTASK).sendToTarget();
		}
	}

	
	public void execute() {
		if( null != mDialogMsg)
			buildDialogTask();
		
		getNextTask();
	}
	
	private void buildDialogTask(){
		Task dialogShowTask = new Task("showDialog", false) {
			
			@Override
			public TaskParm onExecute(TaskParm param)  throws Exception{
				mProgressDialog = new ProgressDialog(mContext);
				mProgressDialog.show();
				mProgressDialog.setMessage(mDialogMsg);
				mProgressDialog.setCancelable(cancleable);
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.setOnCancelListener(new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						cancleAllTask();
					}
				});
				return param;
			}
			
			@Override
			public void onCancle() {
				super.onCancle();
			}

		};
		
		Task dialogCloseTask = new Task("closeDialog",false) {
			@Override
			public TaskParm onExecute(TaskParm param) throws Exception {
				if (null != mProgressDialog) {
					mProgressDialog.cancel();
				}
				return param;
			}
			@Override
			public void onCancle() {
				super.onCancle();
			}
		};
		
		mTaskList.add(0, dialogShowTask);
		mTaskList.add(dialogCloseTask);
	}
	
	public void cancleAllTask(){
		for (Task task : mTaskList) {
			task.onCancle();
		}
		mTaskList.clear();
	}

}
