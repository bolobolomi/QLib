package com.bongn.qlib.utils;

import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

/**
 * <pre>
 * <b>Log日志打印工具类</b>
 * 示例：
 * LogUtil.v("打印一条日志");
 * LogUtil.v("宽等于%d",width);
 * LogUtil.v("宽等于%d、高等于%d",width,height);
 * </pre>
 */
public class LogUtil {
	
	private static final boolean LOG_ENABLE = true;
	
	private static final Logger LOGGER = LoggerManager.getLogger("SSFWAndroid");

	public static void v(String messageFormat, Object... args) {
		if (LOG_ENABLE) {
			LOGGER.v(messageFormat, args);
		}
	}

	public static void d(String messageFormat, Object... args) {
		if (LOG_ENABLE) {
			LOGGER.d(messageFormat, args);
		}
	}

	public static void i(String messageFormat, Object... args) {
		if (LOG_ENABLE) {
			LOGGER.i(messageFormat, args);
		}
	}

	public static void w(String messageFormat, Object... args) {
		if (LOG_ENABLE) {
			LOGGER.w(messageFormat, args);
		}
	}

	public static void e(String messageFormat, Object... args) {
		if (LOG_ENABLE) {
			LOGGER.e(messageFormat, args);
		}
	}

}
