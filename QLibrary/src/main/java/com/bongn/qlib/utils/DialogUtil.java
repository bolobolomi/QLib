package com.bongn.qlib.utils;

import android.content.Context;
import android.view.View;

import com.bongn.qlib.widget.dialog.SweetAlertDialog;
import com.bongn.qlib.widget.dialog.SweetAlertDialog.OnSweetClickListener;

/**
 * Dialog工具类
 * 根据需求不断完善
 * @author Qiang
 *
 */
public class DialogUtil {
	
	/**错误 红叉**/
	public static final int TYPE_ERROR = SweetAlertDialog.ERROR_TYPE;
	/**成功  对勾**/
	public static final int TYPE_SUCCESS = SweetAlertDialog.SUCCESS_TYPE;
	/**警告  叹号**/
	public static final int TYPE_WARNING = SweetAlertDialog.WARNING_TYPE;

    /**没有图标，纯标题文字的**/
    public static final int TYPE_TEXT = SweetAlertDialog.TEXT_TYPE;

	/**
	 * 有 YES 和 NO 按钮的对话框
	 * @param context
	 * @param title 标题
	 * @param content 内容
	 * @param textNoBtn NO按钮蚊子
	 * @param textYesBtn YES按钮蚊子
	 * @param noListener NO按钮点击事件
	 * @param yesListener YES按钮点击事件
	 */
	public static void dialogWithYesNo(Context context , int type , String title , String content , String textNoBtn , String textYesBtn  , OnSweetClickListener noListener ,OnSweetClickListener yesListener ){
		new SweetAlertDialog(context, type)
        .setTitleText(title)
        .setContentText(content)
        .setCancelText(textNoBtn)
        .setConfirmText(textYesBtn)
        .showCancelButton(true)
        .setCancelClickListener(noListener)
        .setConfirmClickListener(yesListener)
        .show();
	}
	
	
	/**
	 * 带标题的消息对话框
	 * @param context
	 * @param title
	 * @param content 消息内容
	 * @param cancelable 是否可以取消掉
	 * @param canceledOnTouchOutside 触摸对话框外是否可以取消
	 */
	public static void dialogMsg(Context context , String title , String content , boolean cancelable , boolean canceledOnTouchOutside){
		SweetAlertDialog sd = new SweetAlertDialog(context);
		sd.setContentText(content);
		if(null != title){
			sd.setTitleText(title);
		}
        sd.setCancelable(cancelable);
        sd.setCanceledOnTouchOutside(canceledOnTouchOutside);
        sd.show();
	}
	
	/**
	 * 带一个按钮的对话框
	 * @param context
	 * @param type
	 * @param title
	 * @param content
	 * @param textYesBtn
	 * @param yesListener
	 */
	public static void dialogWhthYes(Context context , int type , String title , String content , String textYesBtn  , OnSweetClickListener yesListener ){
		new SweetAlertDialog(context, type)
        .setTitleText(title)
        .setContentText(content)
        .setConfirmText(textYesBtn)
        .setConfirmClickListener(yesListener)
        .show();
	}


    /**
     * 带一个按钮的自定义布局的对话框
     * @param context
     * @param type
     * @param title
     * @param view
     * @param textYesBtn
     * @param yesListener
     */
    public static void dialogCustomViewWithYes(Context context , int type , String title , View view ,  String textYesBtn  ,OnSweetClickListener yesListener){
        dialogCustomViewWithYesNo(context, type, title, view, null, textYesBtn, null, yesListener);
    }
    /**
     * 带两个按钮的自定义布局的对话框
     * @param context
     * @param type
     * @param title
     * @param view
     * @param textNoBtn
     * @param textYesBtn
     * @param noListener
     * @param yesListener
     */
    public static void dialogCustomViewWithYesNo(Context context , int type , String title , View view , String textNoBtn , String textYesBtn  , OnSweetClickListener noListener ,OnSweetClickListener yesListener ){
        SweetAlertDialog dialog = new SweetAlertDialog(context, type);
        dialog.setTitleText(title);
        dialog.setConfirmText(textYesBtn);
        dialog.setCustomView(view);
        dialog.setConfirmClickListener(yesListener);
        dialog.setCancelable(false);
        if(null != textNoBtn){
            dialog.showCancelButton(true);
            dialog.setCancelClickListener(noListener);
            dialog.setCancelText(textNoBtn);
        }
        dialog.show();
    }
}
