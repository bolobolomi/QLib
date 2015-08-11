package com.bongn.qlib.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 描述：Date 类实际上只是一个包裹类, 它包含的是一个长整型数据, 表示的是从GMT(格林尼治标准时间)
 * 1970年1月1日00:00:00这一刻之前或者是之后经历的毫秒数。
 * 
 * @author 梁焱
 * @version 1.9
 * @since 2007-03-09
 */

public class DateUtilz {

	/** Number of milliseconds in a standard second. */
	public static final long MILLIS_PER_SECOND = 1000;

	/** Number of milliseconds in a standard minute. */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

	/** Number of milliseconds in a standard hour. */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

	/** Number of milliseconds in a standard day. */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	/** 默认日期格式yyyy-MM-dd */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/** 在组装时间时不改变的部分采用此值 */
	public static final int TIME_NO_CHANGE = -1;

	/** 表示时间为0 */
	public static final int TIME_IS_ZERO = 0;

	// NOTE 获得当前时间
	/**
	 * 获得当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得某个时间的年份
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的年份
	 */
	public static int getSomeYear(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得某个时间的月份
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的月份
	 */
	public static int getSomeMonth(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当前DAY OF MONTH
	 * 
	 * @return 当前DAY OF MONTH
	 */
	public static int getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得某个时间的DAY OF MONTH
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的DAY OF MONTH
	 */
	public static int getSomeDay(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前小时
	 * 
	 * @return 当前小时
	 */
	public static int getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获得某个时间的小时
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的小时
	 */
	public static int getSomeHour(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获得当前分钟
	 * 
	 * @return 当前分钟
	 */
	public static int getCurrentMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 获得某个时间的分钟
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的分钟
	 */
	public static int getSomeMinute(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 获得当前秒
	 * 
	 * @return 当前秒
	 */
	public static int getCurrentSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 获得某个时间的秒数
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的秒数
	 */
	public static int getSomeSecond(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 获得当前毫秒
	 * 
	 * @return 当前毫秒
	 */
	public static int getCurrentMillisecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * 获得某个时间的毫秒
	 * 
	 * @param date
	 *            某个时间
	 * @return 某个时间的毫秒
	 */
	public static int getSomeMillisecond(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * 获得当前的日期
	 * 
	 * @return 返回当前的日期
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 获得昨天日期
	 * 
	 * @return 返回昨天日期
	 */
	public static Date getYestodayDate() {
		return new Date(System.currentTimeMillis() - MILLIS_PER_DAY);
	}

	/**
	 * 获得昨天日期
	 * 
	 * @param date
	 * @return 返回昨天日期
	 */
	public static Date getYestodayDate(Date date) {
		return new Date(date.getTime() - MILLIS_PER_DAY);
	}

	/**
	 * 获得明天日期
	 * 
	 * @return 返回明天日期
	 */
	public static Date getTomorrowDate() {
		return new Date(System.currentTimeMillis() + MILLIS_PER_DAY);
	}

	/**
	 * 获得指定日期的明天的日期
	 * 
	 * @param date
	 * @return 返回明天日期
	 */
	public static Date getTomorrowDate(Date date) {
		return new Date(date.getTime() + MILLIS_PER_DAY);
	}

	// NOTE 获得指定格式的时间

	/**
	 * 获得当前的日期(yyyy-MM-dd)
	 * 
	 * @return 返回当前的日期
	 */
	public static String getCurrentDateAccurateToDay() {
		return getCurrentDate(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 获得当前的日期(yyyy-MM-dd EE)
	 * 
	 * @return 返回当前的日期
	 */
	public static String getCurrentDateAccurateToWeek() {
		return getCurrentDate("yyyy-MM-dd EE");
	}

	/**
	 * 获得当前的时间(yyyy-MM-dd hh:mm:ss)
	 * 
	 * @return 返回当前时间
	 */
	public static String getCurrentDateAccurateToSecond() {
		return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得当前的时间(yyyyMMddHHmmssSSS)
	 * 
	 * @return 返回当前时间
	 */
	public static String getCurrentDateAccurateToMillisecond() {
		return getCurrentDate("yyyy-MM-dd HH:mm:ss,SSS");
	}

	/**
	 * 获得明天日期(yyyy-MM-dd)
	 * 
	 * @return 返回明天日期
	 */
	public static String getTomorrowStringDate() {
		return getTomorrowDate(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 获得昨天日期(yyyy-MM-dd)
	 * 
	 * @return 返回昨天日期
	 */
	public static String getYestodayStringDate() {
		return getYestodayDate(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 按格式获得昨天日期 例如：getYestodayDate("yyyy-MM-dd");
	 * 
	 * @param pattern
	 * @return 返回昨天日期
	 */
	public static String getYestodayDate(String pattern) {
		Date yestodayDate = new Date(System.currentTimeMillis() - MILLIS_PER_DAY);
		return dateToString(yestodayDate, pattern);
	}

	/**
	 * 按格式获得明天日期 例如，getTomorrowDate("yyyy-MM-dd");
	 * 
	 * @param pattern
	 * @return 返回明天日期
	 */
	public static String getTomorrowDate(String pattern) {
		Date tomorrowDate = new Date(System.currentTimeMillis() + MILLIS_PER_DAY);
		return dateToString(tomorrowDate, pattern);
	}

	/**
	 * 返回指定格式的当天日期 例如：getCurrentDate("yyyy-MM-dd");
	 * 
	 * @param pattern
	 *            时间样式
	 * @return 返回指定格式的当天日期
	 */
	public static String getCurrentDate(String pattern) {
		return dateToString(new Date(), pattern);
	}

	// NOTE 转换日期格式

	/**
	 * 转换日期格式
	 * 
	 * @param date
	 * @param oldPattern
	 * @param newPattern
	 * @return 转换格式后的日期
	 */
	public static String getDateByFormat(String date, String oldPattern, String newPattern) {
		Date newDate = stringToDate(date, oldPattern);
		return dateToString(newDate, newPattern);
	}

	/**
	 * Date转换为String
	 * 
	 * @param date
	 * @param pattern
	 * @return 字符串格式日期
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * String转换为Date
	 * 
	 * 说明：SimpleDateFormat是DateFormat的子类。
	 * 在DateFormat中，也可以调用parse方法来解析日期型的数据，不过不推荐这样做。
	 * 建议还是采用SimpleDateFormat类中parse(Strng source, ParsePosition positon)来解析。
	 * 具体原因，因为DateFormat中的parse方法有bug且不支持定位解析。
	 * 
	 * @param date
	 * @return 分析成功则返回分析得到的日期，失败则返回null
	 */
	public static Date stringToDate(String date, String pattern) {
		return stringToDate(date, pattern, Locale.CHINA);
	}

	/**
	 * String转换为Date
	 * 
	 * @param date
	 * @param pattern
	 * @param locale
	 * @return 分析成功则返回分析得到的日期，失败则返回null
	 */
	public static Date stringToDate(String date, String pattern, Locale locale) {
		SimpleDateFormat formate = new SimpleDateFormat(pattern, locale);
		Date newDate = formate.parse(date, new ParsePosition(0));
		return newDate;
	}

	/**
	 * String转换为Calendar
	 * 
	 * @param date
	 * @param pattern
	 * @return Calendar
	 */
	public static Calendar stringToCalendar(String date, String pattern) {
		Date newDate = stringToDate(date, pattern);
		return dateToCalendar(newDate);
	}

	/**
	 * Calendar转Date
	 * 
	 * @param cal
	 * @return Date
	 */
	public static Date calendarToDate(Calendar cal) {
		return cal.getTime();
	}

	/**
	 * Date转Calendar
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * java.util.Date转java.sql.Date
	 * 
	 * @param date
	 * @return java.sql.Date
	 */
	public static java.sql.Date utilDateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.sql.Date转java.util.Date
	 * 
	 * @param date
	 * @return java.util.Date
	 */
	public static Date sqlDateToUtilDate(java.sql.Date date) {
		return new Date(date.getTime());
	}
	
	/**
	 * Timestamp转String
	 * 
	 * @param Timestamp
	 * @return String
	 */
	public static String timestampToString(Timestamp tsDdate, String pattern) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			// 方法一
			tsStr = sdf.format(tsDdate);
			System.out.println(tsStr);
			// 方法二
			// tsStr = ts.toString();
			// System.out.println(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tsStr;
	}

	// NOTE 获得特定的日期

	/**
	 * 获得某一日期的yyyy-MM-dd 00:00:00形式
	 * 
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static Date getSomeDateAccurateToMinHour(Date date) {
		Map map = new HashMap();
		map.put(new Integer(Calendar.HOUR_OF_DAY), new Integer(TIME_IS_ZERO));
		map.put(new Integer(Calendar.MINUTE), new Integer(TIME_IS_ZERO));
		map.put(new Integer(Calendar.SECOND), new Integer(TIME_IS_ZERO));
		return getSomeDate(date, map);
	}

	/**
	 * 获得某一日期的yyyy-MM-dd 23:59:59形式
	 * 
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static Date getSomeDateAccurateToMaxHour(Date date) {
		Map map = new HashMap();
		map.put(new Integer(Calendar.HOUR_OF_DAY), new Integer(23));
		map.put(new Integer(Calendar.MINUTE), new Integer(59));
		map.put(new Integer(Calendar.SECOND), new Integer(59));
		return getSomeDate(date, map);
	}

	/**
	 * 获得某一日期的yyyy-MM-01 00:00:00形式
	 * 
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static Date getSomeDateAccurateToMinMonth(Date date) {
		Map map = new HashMap();
		map.put(new Integer(Calendar.DAY_OF_MONTH), new Integer(1));
		map.put(new Integer(Calendar.HOUR_OF_DAY), new Integer(TIME_IS_ZERO));
		map.put(new Integer(Calendar.MINUTE), new Integer(TIME_IS_ZERO));
		map.put(new Integer(Calendar.SECOND), new Integer(TIME_IS_ZERO));
		return getSomeDate(date, map);
	}

	/**
	 * 获得某一日期的yyyy-MM-dd(dd为此月最后一天) 23:59:59形式
	 * 
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static Date getSomeDateAccurateToMaxMonth(Date date) {
		Map map = new HashMap();
		map.put(new Integer(Calendar.DAY_OF_MONTH), new Integer(getDaysOfMonth(date)));
		map.put(new Integer(Calendar.HOUR_OF_DAY), new Integer(23));
		map.put(new Integer(Calendar.MINUTE), new Integer(59));
		map.put(new Integer(Calendar.SECOND), new Integer(59));
		return getSomeDate(date, map);
	}

	/**
	 * 根据日期组装新的日期
	 * 
	 * @param date
	 * @param calendar
	 * @return Date
	 */
	public static Date getSomeDate(Date date, Map calendar) {
		Calendar cal = dateToCalendar(date);

		Iterator it = calendar.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			Integer key = (Integer) entry.getKey();
			Integer value = (Integer) entry.getValue();
			if (key.intValue() == Calendar.MONTH) {
				cal.set(key.intValue(), value.intValue() - 1);
			} else {
				cal.set(key.intValue(), value.intValue());
			}
		}
		return calendarToDate(cal);
	}

	/**
	 * 计算某年第几天的日期
	 * 
	 * @param year
	 *            年份
	 * @param dayOfYear
	 *            一年中的第几天
	 * @return 某年第几天的日期
	 */
	public static String getDateWithDayOfYear(int year, int dayOfYear) {
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return df.format(cal.getTime());
	}

	/**
	 * 获得当前星期中某一天的日期
	 * 
	 * @param dayOfWeek
	 *            当前星期中的某一天
	 * @return 当前星期中某一天的日期
	 */
	public static String getDateWithDayOfCurrentWeek(int dayOfWeek) {
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		if (dayOfWeek == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 7);
		}
		return df.format(cal.getTime());
	}

	// NOTE 时期计算
	/**
	 * 获得某个月的天数
	 * 
	 * @param date
	 *            日期
	 * @return 某个月的天数
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, getSomeYear(date));
		cal.set(Calendar.MONTH, getSomeMonth(date) - 1);

		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回某年的天数
	 * 
	 * @param year
	 * @return 某年的天数
	 */
	public static int getDaysOfYear(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

	/**
	 * 日期计算，当前日期加或减掉相应的小时数
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时数
	 * @return 返回计算后的日期
	 */
	public static Date calculateWithHour(Date date, int hour) {
		Calendar cal = dateToCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}

	/**
	 * 日期计算，当前日期加或减掉相应的天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回计算后的日期
	 */
	public static Date calculateWithDay(Date date, int day) {
		Calendar cal = dateToCalendar(date);
		cal.setTimeInMillis(cal.getTimeInMillis() + day * MILLIS_PER_DAY);
		return cal.getTime();
	}

	/**
	 * 日期计算，当前日期加或减掉相应的月
	 * 
	 * @param date
	 *            日期
	 * @param month
	 *            月份数
	 * @return 返回计算后的日期
	 */
	public static Date calculateWithMonth(Date date, int month) {
		Calendar cal = dateToCalendar(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 日期计算，当前日期加或减掉相应的年数
	 * 
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @return 返回计算后的日期
	 */
	public static Date calculateWithYear(Date date, int year) {
		Calendar cal = dateToCalendar(date);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	/**
	 * 日期计算，当前日期加或减掉相应的小时数
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时数
	 * @return 返回计算后的日期
	 */
	public static String calculateWithHour(String date, int hour) {
		Date temp = calculateWithHour(stringToDate(date, DEFAULT_DATE_PATTERN), hour);
		return dateToString(temp, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 日期计算，当前日期加或减掉相应的天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回计算后的日期
	 */
	public static String calculateWithDay(String date, int day) {
		Date temp = calculateWithDay(stringToDate(date, DEFAULT_DATE_PATTERN), day);
		return dateToString(temp, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 日期计算，当前日期加或减掉相应的月数
	 * 
	 * @param date
	 *            日期
	 * @param month
	 *            月数
	 * @return 返回计算后的日期
	 */
	public static String calculateWithMonth(String date, int month) {
		Date temp = calculateWithMonth(stringToDate(date, DEFAULT_DATE_PATTERN), month);
		return dateToString(temp, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 日期计算，当前日期加或减掉相应的年数
	 * 
	 * @param date
	 *            日期
	 * @param year
	 *            年数
	 * @return 返回计算后的日期
	 */
	public static String calculateWithYear(String date, int year) {
		Date temp = calculateWithYear(stringToDate(date, DEFAULT_DATE_PATTERN), year);
		return dateToString(temp, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 计算两天之间的天数，日期格式为yyyy-MM-dd Calculates the number of days between two
	 * calendar days in a manner which is independent of the Calendar type used.
	 * 
	 * @param date1
	 *            The first date.
	 * @param date2
	 *            The second date.
	 * 
	 * @return The number of days between the two dates. Zero is returned if the
	 *         dates are the same, one if the dates are adjacent, etc. The order
	 *         of the dates does not matter, the value returned is always >= 0.
	 *         If Calendar types of d1 and d2 are different, the result may not
	 *         be accurate.
	 */
	public static int getBetweenDays(Date date1, Date date2) {

		Calendar d1 = dateToCalendar(date1);
		Calendar d2 = dateToCalendar(date2);
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算两个时间之间的分钟数，小时格式为 HH:mm
	 * 
	 * @param hour1
	 * @param hour2
	 * @return 两时间分钟之差
	 */
	public static int getBetweenMinute(Date date1, Date date2) {
		return (int) (getBetweenMillis(date1, date2) / MILLIS_PER_MINUTE);
	}

	/**
	 * 计算两个时间之间的分钟数，小时格式为 HH:mm
	 * 
	 * @param hour1
	 * @param hour2
	 * @return 两时间分钟之差
	 */
	public static int getBetweenSecond(Date date1, Date date2) {
		return (int) (getBetweenMillis(date1, date2) / MILLIS_PER_SECOND);
	}

	/**
	 * 计算两个小时之间的毫秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return 两时间毫秒之差
	 */
	public static long getBetweenMillis(Date date1, Date date2) {
		Calendar d1 = dateToCalendar(date1);
		Calendar d2 = dateToCalendar(date2);
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		return d2.getTimeInMillis() - d1.getTimeInMillis();
	}

	// NOTE 判断日期
	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 * @return true是闰年，false不是闰年
	 */
	public static boolean isLeapYear(int year) {

		boolean isLeapYear = false;
		int gregorianCutoverYear = 1582;// 1582 年定的 Gregorian Calendar
										// 才再增加百年不潤四百年潤的規則
		if (year >= gregorianCutoverYear) {
			if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
				isLeapYear = true;
			}
		} else {
			if (year % 4 == 0) {
				isLeapYear = true;
			}
		}
		return isLeapYear;
	}


	/**
	 * 比较时间的新旧
	 * 
	 * @param oldDate
	 *            旧时间
	 * @param newDate
	 *            新时间
	 * @param pattern
	 *            时间样式
	 * @return true 如果newDate比oldDate新
	 */
	public static boolean compareBothDate(String oldDate, String newDate, String pattern) {
		Calendar c1 = stringToCalendar(oldDate, pattern);
		Calendar c2 = stringToCalendar(newDate, pattern);
		return (c2.getTimeInMillis() - c1.getTimeInMillis()) >= 0;
	}

	/**
	 * 获得当前时间是一周的第几天
	 * 
	 * @param date
	 * @return 周一到周日分别是 1-7
	 */
	public static int getDayOfweek() {
		int week = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		week = calendar.get(Calendar.DAY_OF_WEEK);
		week = week - 1;
		if (week == 0) {
			week = 7;
		}
		return week;
	}

	/**
	 * 获得当前的时间(yyyyMMdd)
	 * 
	 * @return 返回当前时间
	 */
	public static String getLittleDate() {
		return getCurrentDate("yyyyMMdd");
	}

	/**
	 * 获得当前的时间(yyyyMMddHHmmss)
	 * 
	 * @return 返回当前时间
	 */
	public static String getToSecond() {
		return getCurrentDate("yyyyMMddHHmmss");
	}

	/**
	 * 测试用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * SimpleDateFormat formate = new SimpleDateFormat("EEE MMM dd HH:mm:ss
		 * zzz yyyy",Locale.US); java.util.Date newDate = formate.parse("Wed Aug
		 * 18 16:05:57 CST 2010", new ParsePosition(0));
		 * System.out.println(newDate);
		 */
		/*
		 * SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd
		 * HH:mm:ss"); java.util.Date newDate = formate.parse("2011-08-08
		 * 23:18:00", new ParsePosition(0));
		 * System.out.println(DateUtilz.getBetweenDays(new Date(), newDate));
		 */
		// long timemill = Long.parseLong("1332347019000");
		// long timemilloff = timemill - 420*60*1000;
		// /Date datea = new Date(Long.parseLong(timemilloff));
		DateUtilz a = new DateUtilz();
		System.out.println(a.calculateWithHour(new Date(), -1));
		// getCurrentDateAccurateToMillisecond
		// System.out.println(new Date(Long.parseLong));
	}
}
