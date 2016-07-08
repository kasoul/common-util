package com.spuerh.hz.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */

public class ChinaFestivalUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(ChinaFestivalUtil.class);
	private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");

	
	/***
	 * 输入日期，得到该日期所需要的历史数据的dates（日期集合）
	 * 
	 * @param date
	 *            //预测的日期
	 * @param datesSize
	 *            //需要历史数据的天数
	 * @return List<String>（日期集合）
	 */

	public static List<String> getParamDate(String date, int datesSize) {

		if (date.length() != 8) {
			logger.error("输入时间格式不正确，请输入\"yyyyMMdd\"");
			return null;
		}
		List<String> dayList = new ArrayList<String>();
		int dayType = getDateType(date);
		if (dayType == 0 || dayType == 1 || dayType == 2) {
			dayList = getParamArroundDates(date, dayType, datesSize);
		}
		return dayList;
	}

	/**
	 * 
	 * @param today（输入日期）
	 * @return -2 ，-1，0，1，2 其中-2：日期输入错误 才 0：工作日 1：周末 2：三天假期
	 * 
	 */
	public static int getDateType(String today) {
		int type = -2;
		if (today.length() != 8)
			return -2;
		// int[] result = new int[kindOfFestival];
		// 元旦日期
		String newYearDay = today.substring(0, 4) + "0101";
		int result = getHolidayArroundType(today, newYearDay);
		if (result == 0 || result == 2) {
			return type = result;
		}
		// 清明日期
		int num = getQingMing(today);
		String qingMingDay = today.substring(0, 4) + "040" + num;
		result = getHolidayArroundType(today, qingMingDay);
		if (result == 0 || result == 2) {
			return type = result;
		}
		// 劳动节日期
		String laborDay = today.substring(0, 4) + "0501";
		result = getHolidayArroundType(today, laborDay);
		if (result == 0 || result == 2) {
			return type = result;
		}
		// 端午日期
		String dragonDay = LunarConvertor.lunarTosolar(today.substring(0, 4) + "-05-05");
		String sunDragonDay = dragonDay.substring(0, 4) + dragonDay.substring(5, 7) + dragonDay.substring(8, 10);
		result = getHolidayArroundType(today, sunDragonDay);
		if (result == 0 || result == 2) {
			return type = result;
		}
		// 中秋日期
		String midAutumnDay = LunarConvertor.lunarTosolar(today.substring(0, 4) + "-08-15");
		String sunMidAutumnDay = midAutumnDay.substring(0, 4) + midAutumnDay.substring(5, 7)
				+ midAutumnDay.substring(8, 10);
		result = getHolidayArroundType(today, sunMidAutumnDay);
		if (result == 0 || result == 2) {
			return type = result;
		}

		if (isWeekend(today)) {
			type = 1;
		} else {
			type = 0;
		}
		return type;
	}

	/**
	 * 判断是否属于假期周边日期类型,并返回类型码
	 * 
	 * @param date
	 * @return -2 ，-1，0，1，2 -2：日期输入错误 -1：不在假日周边 0：工作日 1：周末 2：三天假期
	 */
	private static int getHolidayArroundType(String today, String Holiday) {
		int flag = -1;
		if (today.length() != 8)
			return -2;
		int dayofweek = dayForWeek(Holiday);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(Holiday);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				flag = Integer.parseInt(holidayDuartion.substring(i, i + 1));
			}
		}
		return flag;
	}

	/**
	 * 判断是否属于元旦假期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isNewYearHoliday(String today) {
		boolean flag = false;
		if (today.length() != 8)
			return false;
		String newYearDay = today.substring(0, 4) + "0101";
		int dayofweek = dayForWeek(newYearDay);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(newYearDay);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				if (holidayDuartion.substring(i, i + 1).equals("2")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否属于清明假期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isQingMingHoliday(String today) {
		boolean flag = false;
		if (today.length() != 8)
			return false;
		int num = getQingMing(today);
		String ingMingDay = today.substring(0, 4) + "040" + num;
		int dayofweek = dayForWeek(ingMingDay);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(ingMingDay);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				if (holidayDuartion.substring(i, i + 1).equals("2")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否属于劳动节假期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isLaborHoliday(String today) {
		boolean flag = false;
		if (today.length() != 8)
			return false;
		String LaborDay = today.substring(0, 4) + "0501";
		int dayofweek = dayForWeek(LaborDay);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(LaborDay);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				if (holidayDuartion.substring(i, i + 1).equals("2")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否属于端午节假期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isTheDragonHoliday(String today) {
		boolean flag = false;
		if (today.length() != 8)
			return false;
		String dragonDay = LunarConvertor.lunarTosolar(today.substring(0, 4) + "-05-05");
		// System.out.println(lunarday);
		String sunDragonDay = dragonDay.substring(0, 4) + dragonDay.substring(5, 7) + dragonDay.substring(8, 10);
		// System.out.println(sunDragonDay);
		int dayofweek = dayForWeek(sunDragonDay);
		// System.out.println(dayofweek);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(sunDragonDay);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				if (holidayDuartion.substring(i, i + 1).equals("2")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否属于中秋节假期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isMidAutumnHoliday(String today) {
		boolean flag = false;
		if (today.length() != 8)
			return false;
		String midAutumnDay = LunarConvertor.lunarTosolar(today.substring(0, 4) + "-08-15");
		// System.out.println(lunarday);
		String sunMidAutumnDay = midAutumnDay.substring(0, 4) + midAutumnDay.substring(5, 7)
				+ midAutumnDay.substring(8, 10);
		// System.out.println(sunMidAutumnDay);
		int dayofweek = dayForWeek(sunMidAutumnDay);
		// System.out.println(dayofweek);
		String holidayDuartion = getHolidayDuartion(dayofweek, 3);
		// Calendar cal = Calendar.getInstance();
		String[] arroundDays = getDatesArroundDay(sunMidAutumnDay);

		for (int i = 0; i < 7; i++) {
			if (arroundDays[i].equals(today)) {
				if (holidayDuartion.substring(i, i + 1).equals("2")) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 计算day的参数系数的日期集合
	 * 
	 * @param day
	 * @return
	 */
	private static List<String> getParamArroundDates(String day, int type, int datesSize) {

		List<String> dates = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = formatDate.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int intOfDay = calendar.get(Calendar.DATE);
		int index = 1;
		while (dates.size() < datesSize) {
			calendar.setTime(date);
			calendar.set(Calendar.DATE, intOfDay - index);
			if (getDateType(formatDate.format(calendar.getTime())) == type) {
				dates.add(formatDate.format(calendar.getTime()));
				System.out.println(formatDate.format(calendar.getTime()));
			}
			index++;
		}
		return dates;
	}

	/**
	 * 获得day的前三天和后三天
	 * 
	 * @param day
	 * @return
	 */
	private static String[] getDatesArroundDay(String day) {
		String[] dates = new String[7];
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = formatDate.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int intOfDay = calendar.get(Calendar.DATE);
		for (int i = 0; i < 7; i++) {
			calendar.setTime(date);
			calendar.set(Calendar.DATE, intOfDay + i - 3);
			dates[i] = formatDate.format(calendar.getTime());
			// System.out.println(formatDate.format(calendar.getTime()));
		}
		return dates;

	}

	/**
	 * 假期怎么放
	 * 
	 * @param date
	 * @return
	 */
	private static String getHolidayDuartion(int dayofweek, int daynum) {
		// int[] offset = new int[7];
		String offset = "";
		if (daynum == 3) {
			switch (dayofweek) {
			case 1:
				offset = "0222000";
				break;
			case 2:
				offset = "0222000";
				break;
			case 3:
				offset = "0022200";
				break;
			case 4:
				offset = "0002220";
				break;
			case 5:
				offset = "0002220";
				break;
			case 6:
				offset = "0022200";
				break;
			case 7:
				offset = "0222000";
				break;
			default:
				offset = "";
				break;
			}
		} else {

		}
		// return offset=;
		return offset;
	}

	/**
	 * 判断是否是周末
	 * 
	 * @param pTime
	 * @return
	 */
	public static boolean isWeekend(String pTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		if (dayForWeek <= 5) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 判断星期几
	 * 
	 * @param pTime
	 * @return
	 */
	public static int dayForWeek(String pTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 根据日期，判断今年的清明节是公历的date
	 * 
	 * @param day
	 * @return
	 */
	private static int getQingMing(String day) {
		int century = Integer.parseInt(day.substring(0, 2));
		double Y = Double.parseDouble(day.substring(2, 4));
		// System.out.println("Y"+Y);
		double D = 0.2422;
		double C = 0;
		if (century == 20) {
			C = 4.81;
		} else if (century == 19) {
			C = 5.59;
		} else {
			return -1;
		}

		int L = (int) Y / 4;
		int result = (int) ((Y * D + C) - L);
		return result;

	}
	
	public static void main(String[] args) {
		String day = "20160111";
		System.out.println(getQingMing(day));
		System.out.println("20160111".length());
		System.out.println(getDateType(day));
		System.out.println(getDateType("20160112"));
		getParamDate("20160101",10);
	}
	
}
