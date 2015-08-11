/**    
 * @Title: TaskManager2.java  
 * @Package com.im_cmcc.common.utils.taskutils
 */
package com.bongn.qlib.utils.task;

import java.util.Map;


import android.content.Context;

import com.bongn.qlib.exception.HTTPException;
import com.bongn.qlib.utils.NetworkUtils;


/**
 * @ClassName: TaskManager2
 * @Description: 一次执行一个的异步任务类。
 * @author WQ
 * @date 2014-5-19 上午9:33:01
 */
public class  TaskManager2 {
	
	private Context mContext;
	private String message;
	private Callback mCallback;
	
	private TaskManager2(Context context , String message) {
		mContext = context;
		this.message = message;
	}

	/**
	* @Description TODO(这里用一句话描述这个方法的作用)
	* @Title init
	* @author WQ
	* @date 2014-5-19 上午11:12:31
	* @param context
	* @param message ProgressDialog 内容
	* @return
	 */
	public static TaskManager2 init(Context context, String message) {

		return new TaskManager2(context,message);

	}
	
	/**
	* @Description get请求
	* @Title get
	* @author WQ
	* @date 2014-5-19 上午11:07:42
	* @param url
	* @param clazz 预期返回类型
	* @param callback 回调函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void get(String url , Class<T> clazz , Callback callback){
		mCallback = callback;
		new GetTask(mContext,message).execute(new Object[]{url,clazz});
	}
	
	

	/**
	* @Description delete请求
	* @Title delete
	* @author WQ
	* @date 2014-5-19 上午11:07:42
	* @param url
	* @param clazz 预期返回类型
	* @param callback 回调函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void delete(String url , Class<T> clazz , Callback callback){
		mCallback = callback;
		new DeleteTask(mContext,message).execute(new Object[]{url,clazz});
	}
	
	/**
	* @Description post 请求
	* @Title post
	* @author WQ
	* @date 2014-5-19 上午11:08:23
	* @param url
	* @param clazz 预期返回参数，目前支持 JSONObject、JSONArray、String
	* @param callback 回调函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void post(String url , Map<String,Object> param , Class<T> clazz , Callback callback){
		mCallback = callback;
		new PostTask(mContext,message).execute(new Object[]{url,clazz,param});
	}
	
	/**
	* @Description 上传文件
	* @Title postFile
	* @author WQ
	* @param url
	* @param clazz 预期返回参数，目前支持 JSONObject、JSONArray、String
	* @param paraMap 请求参数
	* @param fileParamName 文件参数名称数组
	* @param files 文件数组
	* @param callback 回调函数
	 */
//	@SuppressWarnings("unchecked")
//	public <T> void postFile(String url , Class<T> clazz ,HashMap<String,String> param , String[] fileParamName,File [] files, Callback callback){
//		mCallback = callback;
//		new PostFileTask(mContext,message).execute(new Object[]{url,clazz,param,fileParamName,files});
//	}
	/**
	* @Description put 请求
	* @Title put
	* @author WQ
	* @date 2014-5-19 上午11:08:23
	* @param url
	* @param clazz 预期返回参数，目前支持 JSONObject、JSONArray、String
	* @param callback 回调函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void put(String url , String jsonString , Class<T> clazz , Callback callback){
		mCallback = callback;
		new PutTask(mContext,message).execute(new Object[]{url,clazz,jsonString});
	}
	
	
//	class PostFileTask<T> extends BaseTask<Object, T>{
//
//
//		public PostFileTask(Context activity, String message) {
//			super(activity, message);
//		}
//
//		@SuppressWarnings("unchecked")
//		@Override
//		protected void doStuffWithResult(T result) {
//			if(null != mCallback)
//				mCallback.onComplate(result);
//			super.doStuffWithResult(result);
//		}
//		
//		@Override
//		protected void doError(Exception exception) {
//			if(null != mCallback)
//				mCallback.onError(exception);
//			super.doError(exception);
//		}
//		
//		@Override
//		protected void doError(HTTPException exception) {
//			if(null != mCallback)
//				mCallback.onHttpError(exception);
//			super.doError(exception);
//		}
//
//		@SuppressWarnings("unchecked")
//		@Override
//		protected T onExecute(Object... params) throws Exception {
//			String url = params[0].toString();
//			Class<T> clazz = (Class<T>) params[1];
//			
//			HashMap<String,String> p  = null;
//			if(null != params[2])
//				 p = (HashMap<String, String>) params[2];
//			
//			String[] fileParamName = null;
//			if(null != params[3])
//				fileParamName = (String[]) params[3];
//			
//			File [] files = (File[]) params[4];
//			
//			return NetworkUtils.postFile(mContext,  url, clazz, p, fileParamName, files, null,null);
//		}
//		
//	}
	
	class PostTask<T> extends BaseTask<Object, T>{


		public PostTask(Context activity, String message) {
			super(activity, message);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void doStuffWithResult(T result) {
			if(null != mCallback)
				mCallback.onComplate(result);
			super.doStuffWithResult(result);
		}
		
		@Override
		protected void doError(Exception exception) {
			if(null != mCallback)
				mCallback.onError(exception);
			super.doError(exception);
		}
		
		@Override
		protected void doError(HTTPException exception) {
			if(null != mCallback)
				mCallback.onHttpError(exception);
			super.doError(exception);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T onExecute(Object... params) throws Exception {
			String url = params[0].toString();
			Class<T> clazz = (Class<T>) params[1];
			Map<String,Object> param = (Map<String,Object>)params[2];
			return NetworkUtils.post(mContext, url, param, clazz);
		}
		
	}
	
	class PutTask<T> extends BaseTask<Object, T>{


		public PutTask(Context activity, String message) {
			super(activity, message);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void doStuffWithResult(T result) {
			if(null != mCallback)
				mCallback.onComplate(result);
			super.doStuffWithResult(result);
		}
		
		@Override
		protected void doError(Exception exception) {
			if(null != mCallback)
				mCallback.onError(exception);
			super.doError(exception);
		}
		
		@Override
		protected void doError(HTTPException exception) {
			if(null != mCallback)
				mCallback.onHttpError(exception);
			super.doError(exception);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T onExecute(Object... params) throws Exception {
			String url = params[0].toString();
			Class<T> clazz = (Class<T>) params[1];
			String jsonString =  (String) params[2];
			return NetworkUtils.put(mContext, url, jsonString, clazz);
		}
		
	}
	
	class DeleteTask<T> extends BaseTask<Object, T>{


		public DeleteTask(Context activity, String message) {
			super(activity, message);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void doStuffWithResult(T result) {
			if(null != mCallback)
				mCallback.onComplate(result);
			super.doStuffWithResult(result);
		}
		
		@Override
		protected void doError(Exception exception) {
			if(null != mCallback)
				mCallback.onError(exception);
			super.doError(exception);
		}
		
		@Override
		protected void doError(HTTPException exception) {
			if(null != mCallback)
				mCallback.onHttpError(exception);
			super.doError(exception);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T onExecute(Object... params) throws Exception {
			String url = params[0].toString();
			Class<T> clazz = (Class<T>) params[1];
			return NetworkUtils.delete(mContext, url, clazz);
		}
		
		
	}
	

	class GetTask<T> extends BaseTask<Object, T>{


		public GetTask(Context activity, String message) {
			super(activity, message);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void doStuffWithResult(T result) {
			if(null != mCallback)
				mCallback.onComplate(result);
			super.doStuffWithResult(result);
		}
		
		@Override
		protected void doError(Exception exception) {
			if(null != mCallback)
				mCallback.onError(exception);
			super.doError(exception);
		}
		
		@Override
		protected void doError(HTTPException exception) {
			if(null != mCallback)
				mCallback.onHttpError(exception);
			super.doError(exception);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T onExecute(Object... params) throws Exception {
			String url = params[0].toString();
			Class<T> clazz = (Class<T>) params[1];
			return NetworkUtils.get(mContext, url,clazz);
		}
		
		
	}
	
	public static abstract class Callback<T>{
			public abstract void onComplate(T result);
			public void onHttpError(HTTPException exception){}
			public void onError(Exception exception){}
	}
}
