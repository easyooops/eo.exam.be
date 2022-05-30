package com.easyoops.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private DateUtils() {}

	private static String formatStr = "yyyy/MM";
	
	public static String getLastMonthFirstDay() {
		return getLastMonth() + "/01";
	}
	
	public static String getLastMonthLastDay() {
		SimpleDateFormat formater = new SimpleDateFormat(formatStr+"/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getLastMonthDate()); 
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH,0);
		Date date = calendar.getTime();
		return formater.format(date);
	}
	
	
	public static String getLastMonth() {
		SimpleDateFormat formater = new SimpleDateFormat(formatStr);
		Date date = getLastMonthDate();
		return formater.format(date);
	}
	
	public static String getThisMonth() {
		SimpleDateFormat formater = new SimpleDateFormat(formatStr);
		Date date = new Date();
		return formater.format(date);
	}
	
	public static Date getLastMonthDate() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); 
		date = calendar.getTime();
		return date;
	}
	
	public static String serviceRequestDateStr(Calendar dayc1) {
		return new StringBuilder()
				.append(dayc1.get(Calendar.YEAR))
				.append("-")
				.append(String.format("%02d",(dayc1.get(Calendar.MONTH) + 1)))
				.append("-")
				.append(String.format("%02d",(dayc1.get(Calendar.DAY_OF_MONTH))))
				.append("(")
				.append(DateUtils.dateOfWeek(dayc1.get(Calendar.DAY_OF_WEEK)))
				.append(")")
				.toString();
	}
	
	public static String dateOfWeek(int dayOfWeek) {
		switch (dayOfWeek) {
		case 1:
			return "일";
		case 2:
			return "월";
		case 3:
			return "화";
		case 4:
			return "수";
		case 5:
			return "목";
		case 6:
			return "금";
		case 7:
			return "토";
		default:
			return "";
		}
	}
	
	
}
