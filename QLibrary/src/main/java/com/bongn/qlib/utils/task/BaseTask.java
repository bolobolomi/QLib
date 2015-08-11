package com.bongn.qlib.utils.task;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Pair;

import com.bongn.qlib.exception.HTTPException;
import com.bongn.qlib.utils.LogUtil;
import com.bongn.qlib.widget.dialog.SweetAlertDialog;


/**
 * task基类
 * 
 * @author QIANG
 * 
 * @param <Input>
 * @param <Result>
 */
public abstract class BaseTask<Input, Result> extends AsyncTask<Input, Void, Pair<Exception, Result>> {

	static String tag = BaseTask.class.getName();
	
	protected Context mActivity = null;

	private boolean isShow = true;
	private SweetAlertDialog mProgressDialog;
	private String message;

	public void setShowDialog(boolean isShow) {
		this.isShow = isShow;
	}

	public BaseTask(Context activity, String message) {
		this.mActivity = activity;
		this.message = message;
		mProgressDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
		mProgressDialog.setTitleText(message);
		// mProgressDialog.setCancelable(false);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				doCancelled();
			}
		});
	}

	@Override
	protected void onPreExecute() {
		if (isShow && !message.equals("")) {
			mProgressDialog.show();
		}
		super.onPreExecute();
	}

	@Override
	protected final Pair<Exception, Result> doInBackground(Input... params) {
		Result res = null;
		Exception ex = null;
		try {
			long start = System.currentTimeMillis();
			res = onExecute(params);
			//LogUtils.i(tag, "Executed %s in %d ms." + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			LogUtil.d(e.toString());
			ex = e;
		}
		return new Pair<Exception, Result>(ex, res);
	}

	@Override
	protected final void onPostExecute(Pair<Exception, Result> result) {
		super.onPostExecute(result);
		try {
			if (result.first != null) {
				if(result.first instanceof HTTPException){
					doError((HTTPException)result.first);
				}else{
					doError(result.first);
				}
				
			} else {
				doStuffWithResult(result.second);
			}
		} catch (Throwable t) {
			LogUtil.d(t.toString());
		}
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	protected abstract Result onExecute(Input... params) throws Exception;

	protected void doStuffWithResult(Result result) {
	}

	protected void doError(Exception exception) {
		exception.printStackTrace();
	}
	
	protected void doError(HTTPException exception) {
		exception.printStackTrace();
	}
	
	/**
	 * 处理取消的方法
	 * 
	 */
	protected void doCancelled() {
	}
}
	
