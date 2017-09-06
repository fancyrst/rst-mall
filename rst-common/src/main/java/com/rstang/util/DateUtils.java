package com.rstang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.rstang.util.validate.Assert;
import com.rstang.util.validate.ValidateUtils;

/**
 * Utility method for handle date & time.
 * 
 * @author yexiong
 * @since 1.0
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	/** Date Time Pattern **/
	public static final String DATETIME_YYYYMMDD_HHMMSS_HYPHEN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_YYYYMMDD_HHMM_HYPHEN = "yyyy-MM-dd HH:mm";;
	public static final String DATETIME_YYYYMMDD_HHMMSS = "yyyyMMddHHmmss";
	public static final String DATETIME_YYYYMMDD_HHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String DATETIME_YYYYMMDD_HHMMSS_SLASH = "yyyy/MM/dd HH:mm:ss";
	
	/** Date Pattern **/
	public static final String DATE_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_YYYYMM = "yyyyMM";
	public static final String DATE_YYMMDD = "yyMMdd";
	public static final String DATE_YYYYMMDD_SLASH = "yyyy/MM/dd";
	public static final String DATE_YYYYMMDD_HYPHEN = "yyyy-MM-dd";
	public static final String DATE_DDMMYYYY_SLASH = "dd/MM/yyyy";
	
	/** Time Pattern **/
	public static final String TIME_HHMM = "HHmm";
	public static final String TIME_HHMM_COLON = "HH:mm";
	public static final String TIME_HHMMSS = "HHmmss";
	public static final String TIME_HHMMSS_COLON = "HH:mm:ss";
	
	/**
	 * Get current datetime in format yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		return getCurrentTimeByFmt(DATETIME_YYYYMMDD_HHMMSS_HYPHEN);
	}
	
	/**
	 * Get current date in format yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDate() {
		return getCurrentTimeByFmt(DATE_YYYYMMDD_HYPHEN);
	}
	
	/**
	 * Get current time in format HH:mm:ss
	 * @return HH:mm:ss
	 */
	public static String getCurrentTime() {
		return getCurrentTimeByFmt(TIME_HHMMSS_COLON);
	}
	
	/**
	 * Get current date or time by input date format
	 * @param dateFmt input date formate
	 * @return dateFmt
	 */
	public static String getCurrentTimeByFmt(String dateFmt) {
		return getSimpleDateFormat(dateFmt).format(new Date());
	}
	
	public static SimpleDateFormat getSimpleDateFormat(String dateFmt) {
		if(!ValidateUtils.hasText(dateFmt)) 
			throw new IllegalArgumentException("dateFmt must not be null or empty!");
		
		return new SimpleDateFormat(dateFmt);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * Covert input date from input date format to ouput date format
	 * @param date
	 * @param inputFmt
	 * @param ouputFmt
	 * @return
	 * @throws ParseException
	 */
	public static String fmtDate(String date, String inputFmt, String ouputFmt) throws ParseException {
		try {
			SimpleDateFormat inputSdf = getSimpleDateFormat(inputFmt);
			SimpleDateFormat outputSdf = getSimpleDateFormat(ouputFmt);
			
			Assert.notEmpty(date, "Input date must not be null or empty!");
			Date inputDate = inputSdf.parse(date);
			return outputSdf.format(inputDate);
		} catch (ParseException e) {
			throw e;
		}
	}
	
	/**
	 * Remove all non-digit characters in given date. eg: 2014-12-02 12:02:33 to 20141202120233
	 * @param date
	 * @return
	 */
	public static String removeNonDigit(String date) {
		Assert.notEmpty(date, "Input date must not be null or empty!");
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<date.length(); i++) {
			char c = date.charAt(i);
			if((c>='0') && (c<=9)) 
				sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * To check input date is validate or not
	 * @param date    Date value(yyyyMMdd or yyyy/MM/dd or yyyy-MM-dd)
	 * @return true if input date is validate; false otherwise.
	 */
	public static boolean isDate(String date) {
		
		if (ValidateUtils.isEmpty(date)) 
			return false;
		
		if(date.length() != 8 && date.length() != 10)
			return false;
		
		try {		
			
			String digitDate = removeNonDigit(date);
			if(date.length() != 8)
				return false;
			
			SimpleDateFormat sdf = getSimpleDateFormat(DATE_YYYYMMDD);
			Date loc_date = sdf.parse(digitDate);
			String fmtDate = sdf.format(loc_date);
			if(digitDate.equals(fmtDate))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}		
	}
	
	/**
	 * Check if input date is input date format
	 * @param date   
	 * @param dateFmt
	 * @return true if input date is input date format; false otherwise.
	 */
	public static boolean isDate(String date, String dateFmt) {
		if (ValidateUtils.isEmpty(date)) 
			return false;
		
		Date loc_date = null;
		SimpleDateFormat sdf = null;
		try {					
			sdf = getSimpleDateFormat(DATE_YYYYMMDD);
			loc_date = sdf.parse(date);
		} catch (Exception e) {
			return false;
		}		
		
		String fmtDateString = sdf.format(loc_date);
		if(date.equals(fmtDateString))
			return true;
		else
			return false;
	}
	
	/**
	 * To check input time is validate or not. Valid throught 00:00 - 23:59
	 * @param time 			Time value(HH:mm or HHmm)
	 * @return				true if it is valid time, false otherwise.
	 */
	public static boolean isShortTime(String time) {
		try {
			if(ValidateUtils.isEmpty(time))
				return false;
			
			if(time.length() == 4 || time.length() == 5) {
				String digitTime = removeNonDigit(time);
				
				if(digitTime.length() != 4)
					return false;
				
				SimpleDateFormat sdf = getSimpleDateFormat(TIME_HHMM);
				Date loc_date = sdf.parse(digitTime);
				String fmtDate = sdf.format(loc_date);
				
				if(digitTime.equals(fmtDate))
					return true;
				else
					return false;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * To check input time is validate or not. Valid throught 00:00:00 - 23:59:59
	 * @param time 			Time value(HH:mm:ss or HHmmss)
	 * @return				true if it is valid time, false otherwise.
	 */
	public static boolean isLongTime(String time) {
		try {
			if(ValidateUtils.isEmpty(time))
				return false;
			
			if(time.length() == 6 || time.length() == 8) {
				String digitTime = removeNonDigit(time);
				if(digitTime.length() != 6)
					return false;
				
				SimpleDateFormat sdf = getSimpleDateFormat(TIME_HHMMSS);
				Date loc_date = sdf.parse(time);
				String fmtDate = sdf.format(loc_date);
				
				if(digitTime.equals(fmtDate))
					return true;
				else
					return false;				
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Check the current time is in time range or not
	 * @param startTime  		Start time(HH:mm:ss)
	 * @param endTime			End Time(HH:mm:ss)
	 * @return true if current time is in the given time range; false otherwise.
	 * @throws Exception
	 */
	public static boolean isCurrentTimeInRange(String startTime, String endTime) throws Exception {
		try {
			if(ValidateUtils.isEmpty(startTime) || isLongTime(startTime) ||
					ValidateUtils.isEmpty(endTime) || isLongTime(endTime))
				throw new IllegalArgumentException("Invalid Time, startTime = "+startTime + "; endTime = "+endTime);
			
			SimpleDateFormat sdf = getSimpleDateFormat(TIME_HHMMSS);
			Date loc_curr_time = sdf.parse(getCurrentTimeByFmt(TIME_HHMMSS));
			Date loc_start_time = sdf.parse(removeNonDigit(startTime));
			Date loc_end_time = sdf.parse(removeNonDigit(endTime));
			
			if((loc_curr_time.compareTo(loc_start_time) >=0) && (loc_curr_time.compareTo(loc_end_time) <= 0))
				return true;
			else
				return false;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Compare the order of two dates
	 * @param firstDate 	First date value(yyyyMMdd or yyyy/MM/dd or yyyy-MM-dd)
	 * @param secondDate	Second date value(yyyyMMdd or yyyy/MM/dd or yyyy-MM-dd)
	 * @return 		0 (equal)
	 *   			1 (first date after second date)
	 *              -1(first date before second date)
	 */
	public static int compareDate(String firstDate, String secondDate) throws Exception {
		try {
			if(ValidateUtils.isEmpty(firstDate) || isDate(firstDate) ||
					 ValidateUtils.isEmpty(secondDate) || isDate(secondDate)) 
					 throw new IllegalArgumentException("Invalid date, firstDate = "+firstDate + "; secondDate = "+secondDate);
					 
				int result = getDayDiff(firstDate, secondDate);
				if (result == 0) 
					return 0;
				else if(result > 0)
					return 1;
				else
					return -1;
		} catch (Exception e) {
			throw e;
		}		
	}
	
	/**
	 * Compare the order of given two time.
	 * @param firstTime 	First time value(HHmm or HH:mm)
	 * @param secondTime	Second time vlaue(HHmm or HH:mm)
	 * @return 		0 (equal)
	 *   			1 (first time after second time)
	 *              -1(first time before second time)
	 * @throws Exception
	 */
	public static int compareTime(String firstTime, String secondTime) throws Exception {
		try {
			if(ValidateUtils.isEmpty(firstTime) || isDate(firstTime) ||
					 ValidateUtils.isEmpty(secondTime) || isDate(secondTime)) 
					 throw new IllegalArgumentException("Invalid date, firstDate = "+firstTime + "; secondDate = "+secondTime);
			
			SimpleDateFormat sdf = getSimpleDateFormat(TIME_HHMM);
			Date loc_firstDate = sdf.parse(firstTime);
			Date loc_secondDate = sdf.parse(secondTime);
			
			return loc_firstDate.compareTo(loc_secondDate);
		} catch (Exception e) {
			throw e;
		}		
	}	
	
	/**
	 * Find the day diffrences between two days
	 * @param firstDate			First date value(yyyyMMdd or yyyy/MM/dd or yyyy-MM-dd)
	 * @param secondDate		Second date value(yyyyMMdd or yyyy/MM/dd or yyyy-MM-dd)
	 * @return  the day diffrences between two days
	 * @throws Exception
	 */
	public static int getDayDiff(String firstDate, String secondDate) throws Exception {
		try {
			if(ValidateUtils.isEmpty(firstDate) || isDate(firstDate) ||
					 ValidateUtils.isEmpty(secondDate) || isDate(secondDate)) 
					 throw new IllegalArgumentException("Invalid date, firstDate = "+firstDate + "; secondDate = "+secondDate);
			
			SimpleDateFormat sdf = getSimpleDateFormat(DATE_YYYYMMDD);
			Date loc_firstDate  = sdf.parse(removeNonDigit(firstDate));
			Date loc_secondDate = sdf.parse(removeNonDigit(secondDate));
			
			return (int)((loc_firstDate.getTime() - loc_secondDate.getTime())/1000/60/60/24);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Convert input date object to specific date format.
	 * @param date
	 * @param dateFmt
	 * @return date to string of given format.
	 * @throws Exception
	 */
	public static String convertToStr(Date date, String dateFmt) throws Exception {
		return getSimpleDateFormat(dateFmt).format(date);
	}
	
	/**
	 * Convert input string to date object.
	 * @param date
	 * @param dateFmt
	 * @return
	 * @throws Exception
	 */
	public static Date convertToDate(String date, String dateFmt) throws Exception {
		return getSimpleDateFormat(dateFmt).parse(date);
	}
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
}
