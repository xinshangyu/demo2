package com.example.administrator.demo.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
	}
	public static String getTime_YMDMS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String getTime_MD(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
		return format.format(date);
	}

	public static Date getDate_YMDMS(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		try {
			date=format.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return date;
	}
	public static Date getDate(String strdate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		Date date=new Date();
		try {
			date=sdf.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return date;
	}

	//2010-08-15
	public static String getTimeNow(){
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str = dateFormat.format(new Date());
		return str;
	}


	/**
	 * 
	 * @author MaBo  Data>>Nov 22, 2010
	 * <b>方法描述</b>：获得时间 <p> 
	 * <b>方法流程</b>： 返回格式<p> 
	 * @param  
	 * @return
	 */
   public static String getLongData(){
	 DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	 String str = dateFormat.format(new Date());
		return str;
	}
   
   /**
    * 
    * @author MaBo  Data>>Jan 4, 2011
    * <b>方法描述</b>：将标准时区，转换为北京标准时区 <p> 
    * <b>方法流程</b>： <p> 
    * @param  
    * @return
    */
   public static String convertDate(){
	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = dateFormat.format(new Date());
	   return str;
   }

	/**
	 *
	 * @author MaBo  Data>>Jan 4, 2011
	 * <b>方法描述</b>：将标准时区，转换为北京标准时区 <p>
	 * <b>方法流程</b>： <p>
	 * @param
	 * @return
	 */
	public static String getYMD(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new Date());
		return str;
	}

	/**
	 * 根据时间返回Calendar
	 *
	 * @param dateStr
	 * @return
	 */
	public static Calendar calendarConver(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = dateFormat.parse(dateStr);
			calendar.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;
	}
   
   /**
    * 
    * @author 丁丁  Data>>2011-11-14
    * <b>方法描述</b>：统计字符串长度，汉字为2个字符，其他为一个字符 <p> 
    * <b>方法流程</b>： <p> 
    * @param  
    * @return
   */
   public int length(String value) {
		int valueLength = 0;
		
		try {
			String english = "[a-zA-Z0-9]";
			for (int i = 0; i < value.length(); i++) {
				String temp = value.substring(i, i + 1);
				if (temp.matches(english)) {
					valueLength += 1;
				} else {
					valueLength += 2;
				}
			}
			return valueLength;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueLength;
	}
	/**
	 *
	 * @param day 天数
	 * @return
	 */
	public static String add(int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)-day);
		return 	new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}


	/**
	 *
	 * @param day 天数
	 * @return
	 */
	public static String add(int day, Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)-day);
		return 	new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}



	public static Date StringToDate(String s) {
		Date d = new Date();

		Date date;

		if (s == null)
			return null;

		d = new Date(s);

		date = new Date(d.getTime());

		return date;
	}
	public static Date StringToDate(Object o) {
		Date d = new Date();

		Date date;

		if (o == null)
			return null;

		//		d = new java.util.Date("2004-01-01");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(o.toString());
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		date = new Date(d.getTime());

		return date;
	}

	public static int getSysYear() {
		Date d = new Date();

		Date date = new Date(d.getTime());

		return date.getYear();
	}

	public static int getSysMonth() {
		Date d = new Date();

		Date date = new Date(d.getTime());

		return date.getMonth();
	}

	public static int getSysDay() {
		Date d = new Date();

		Date date = new Date(d.getTime());

		return date.getDay();
	}

	public static String getSysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	public static String getSmallSysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date());
	}

	public static String getTimeLong(String endTime){
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date now;

			now = df.parse(getSysDate());

			Date date=df.parse(endTime);

			long l=now.getTime()-date.getTime();
			long day=l/(24*60*60*1000);
			long hour=(l/(60*60*1000)-day*24);
			long min=((l/(60*1000))-day*24*60-hour*60);

			String str="";
			double result=  ((((day*24)+hour)*60)+min);
			if(result<60){
				str=result+"分钟";
			}else{
				DecimalFormat df2  = new DecimalFormat("###.0");


				str=df2.format(result/60.0)+"小时";
			}
			return  str;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 取得当前时间的日期字符串
	 * @returnt
	 */
	public static String getDateNow()
	{
		Calendar cal = Calendar.getInstance();
		String retStr = "";
		retStr = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) +1) + "-" + cal.get(cal.DAY_OF_MONTH);
		return retStr;
	}

	public static String getDateSerial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		return sdf.format(new Date());
	}

	public static String getDateShortSerialYY() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
		return sdf.format(new Date());
	}
	public static String getDateShortSerial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	public static String getDateLongSerial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(new Date());
	}
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getDateTOString(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getDateYear(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	public static String getDateMonth(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.MONTH)+1);
	}

	public static String getDateDay(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getDateHour(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Date d = new Date();
		try {
			d = sdf.parse(ds);
			calendar.setTime(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	}

	public static String formatDateTime(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String df = null;
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	public static String formatDate(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String df = null;

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	public static String formatDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String df = null;

		df = sdf.format(d);

		return df;
	}

	public static String formatDateYM() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date d = new Date();
		String df = null;

		df = sdf.format(d);

		return df;
	}

	public static String formatShortDateTime(String ds) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String df = null;
		try {
			d = sdf2.parse(ds);
			df = sdf.format(d);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return df;
	}

	/**
	 * 添加(或减小)时间
	 * @param date
	 * @param field 要添加(或减小)的字段(年或月或日或...)
	 * @param amount 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
	 * @return 日期
	 */
	public static Date add(Date date, int field, int amount){
		Date uDate = convert2JavaDate((java.sql.Date) date);
		Calendar ca = Calendar.getInstance();
		ca.setTime(uDate);
		ca.add(field, amount);
		Date uNewDate = ca.getTime();
		Date newDate = convert2SqlDate(uNewDate);
		return newDate;
	}

	/**
	 * 添加(或减小)时间
	 * @param date
	 * @param field 要添加(或减小)的字段(年或月或日或...)
	 * @param amount 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
	 * @param pattern 格式化模式
	 * @return 格式化后的日期字符串
	 */
	public static String add(Date date, int field, int amount, String pattern){

		Date uDate = convert2JavaDate((java.sql.Date) date);
		Calendar ca = Calendar.getInstance();
		ca.setTime(uDate);
		ca.add(field, amount);

		return format(ca.getTime(),pattern);
	}

	/**
	 * 添加(或减小)时间
	 * @param date
	 * @param field 要添加(或减小)的字段(年或月或日或...)
	 * @param amount 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
	 * @param pattern 格式化模式
	 * @return 格式化后的日期字符串
	 * @throws ParseException
	 */
	public static String add(String date, int field, int amount, String pattern) throws ParseException {
		Calendar ca = Calendar.getInstance();
		ca.setTime(parse(date, pattern));
		ca.add(field, amount);

		return format(ca.getTime(),pattern);
	}

	/**
	 * 根据字符串生成日期
	 * @param dateStr
	 * @param pattern
	 * @return Date
	 * @throws ParseException
	 */

	public static Date parse(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat format =  new SimpleDateFormat(pattern);
		return format.parse(dateStr);
	}
	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static String format(Date date, String pattern){
		SimpleDateFormat format =  new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 将java.util.Date转换为java.sql.Date
	 * @param javaDate
	 * @return
	 */
	public static Date convert2SqlDate(Date javaDate){
		java.sql.Date sd;

		sd = new java.sql.Date(javaDate.getTime());
		return sd;

	}

	/**
	 * 将java.sql.Date转换为java.util.Date
	 * @param sqlDate
	 * @return
	 */
	public static Date convert2JavaDate(java.sql.Date sqlDate){
		return new java.sql.Date(sqlDate.getTime());
	}



	/*
	 * 得到当前日期的后一天
	 *
	 * @param specifiedDay
	 *
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		if("".equals(specifiedDay)){//当传入时间为空时默认为当前时间
			specifiedDay=getDate();
		}
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		if("".equals(specifiedDay)){//当传入时间为空时默认为当前时间
			specifiedDay=getDate();
		}
		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day +1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}
	/*
	 * 得到后一年当前日期的前一天
	 *
	 * @param specifiedDay
	 *
	 * @return
	 */
	public static String getSpecifiedDayBefore2(String specifiedDay) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		if("".equals(specifiedDay)){//当传入时间为空时默认为当前时间
			specifiedDay=getDate();
		}
		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.set(Calendar.YEAR, year+1);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day-1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 得到date时间的下一个小时
	 * @param date
	 * @return
	 */
	public static String nextHours(java.util.Date date){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)+1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

	}


	/**
	 * @author 朱雪亮
	 * 拼接日期跟时间
	 * @param yMd  时间字符串
	 * @param hms  日期字符串
	 * @return  拼接后的时间
	 */
	public static java.util.Date concatDate(String yMd, String hms){

		if (yMd == null) {
			yMd = format(new java.util.Date(),"yyyy-MM-dd");

		}
		if (hms==null) {
			hms= format(new java.util.Date(), "HH:mm:ss");
		}
		java.util.Date d = null;
		try {
			d = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yMd.concat(" "+hms));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return d;
	}
	/**
	 * 得到后一年当前日期的前一天,包含小时
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayOrHourBefore(String specifiedDay) {


		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {

			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.set(Calendar.YEAR, year+1);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day);
//		c.set(Calendar.HOUR, c.get(Calendar.HOUR)-1);
//		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, c.get(Calendar.SECOND)-1);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dayBefore;
	}

	public static String getSpecifiedDayOrHourBeforeByJq(String specifiedDay) {

		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.set(Calendar.YEAR, year+1);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day);
//		c.set(Calendar.HOUR, c.get(Calendar.HOUR)-1);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, c.get(Calendar.SECOND)-1);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());

		return dayBefore;
	}
	/**
	 * 得到后一年当前日期的前一天,包含小时
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayOrHourBefore2(String specifiedDay) {

		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.set(Calendar.YEAR, year+1);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day);
//		c.set(Calendar.HOUR, c.get(Calendar.HOUR)-1);
//		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, c.get(Calendar.SECOND)-1);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 判断date 是否在当前日期之前
	 * @param dateStr
	 * @return
	 */
	public static boolean isDateBeforeCurrDate(String dateStr){

		if (dateStr==null) {
			return false;
		}
		SimpleDateFormat simpleDateFormat = null;
		if (dateStr.length()>10) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			Date date = simpleDateFormat.parse(dateStr);
			Date currDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
			return date.before(currDate);

		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获得下一天
	 * @return
	 */
	public static String getNextDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(calendar.getTime());
	}


	/**
	 * 明天  - yyyyMMddHHmm
	 * @return
	 */
	public static String getTomorrow_yyyyMMddHHmm(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 明天  - yyyyMMddHHmm
	 * @return
	 */
	public static String getTomorrow_yyyyMMddHHmm_(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");

		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 下一年的 明天 - yyyyMMddHHmm
	 * @return
	 */
	public static String getTomorrowAndNextYear_yyyyMMddHHmm(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
		calendar.set(calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");

		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
	 * 得到两个日期之间的相差的自然月数，不足一月不计算
	 *
	 * @param nowDate
	 * @param oldDate
	 * @return
	 * @throws ParseException
	 */
	public static int getYearMounthDaysNew(String nowDate, String oldDate) {
		int result = 0;
		int result_M = 0;
		int result_Y = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(sdf.parse(oldDate));
			c2.setTime(sdf.parse(nowDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int startYear = c1.get(Calendar.YEAR);
		int startMonth = c1.get(Calendar.MONTH) + 1;
		int startDay = c1.get(Calendar.DAY_OF_MONTH);

		int endYear = c2.get(Calendar.YEAR);
		int endMonth = c2.get(Calendar.MONTH) + 1;
		int endDay = c2.get(Calendar.DAY_OF_MONTH);
		result_M = endMonth - startMonth;
		result_Y = endYear - startYear;
		result = result_M + result_Y * 12;
		if (startDay > endDay) {
			if (endDay == getDaysOfMonth(endYear, endMonth) && startDay == getDaysOfMonth(startYear, startMonth)) {

			} else {
				result -= 1;
			}
		}
//        else{
//            if (startDay == getDaysOfMonth(startYear,startMonth)&&endDay != getDaysOfMonth(endYear,endMonth)) {
//                result -= 1;
//            }
//        }

		Log.i("TAG", "month=" + result);
//        Log.e("CarQuoteActivity","  nowDate="+nowDate+"  oldDate="+oldDate+
//                "  result_M="+result_M+"  result_Y="+result_Y+"  result="+result);
		return Math.abs(result);
	}

	/**
	 * 获取当月有多少天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断查询时间端 合法
	 *
	 * @param date1
	 * @param date2
	 * @return 0 date1 相等 date2   date1 小于 date2  date1 大于 date2
	 */
	public static Integer compareTime(String date1, String date2) {
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (java.text.ParseException e) {
//            System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		if (result == 0) {
//            System.out.println("c1相等c2");
		} else if (result < 0) {
//            System.out.println("c1小于c2");
		} else {
//            System.out.println("c1大于c2");

		}
		return result;
	}

	/**
	 * 计算两个日期之间的年数
	 *
	 * @param nowDate 当前时间
	 * @param oldDate 初登日期
	 * @return
	 */
	public static int yearMinus(String nowDate, String oldDate) {
		String[] token1 = nowDate.split("-");
		String[] token2 = oldDate.split("-");
		int year1 = Integer.parseInt(token1[0]);
		int month1 = Integer.parseInt(token1[1]);
		int day1 = Integer.parseInt(token1[2]);
		int year2 = Integer.parseInt(token2[0]);
		int month2 = Integer.parseInt(token2[1]);
		int day2 = Integer.parseInt(token2[2]);
		int total = 365 * (year1 - year2) + 30 * (month1 - month2)
				+ (day1 - day2);
		System.out.println("total" + total);
		int year = total / 365;// 按照标准一年365天计算 后期再考虑期间闰年问题
		/**
		 * 闰年计算方法 if((year%4==0&&year%1!=0)||(year%400==0)) {
		 * System.out.println(year+"年是闰年"); }
		 */
		System.out.println("year" + year);
		return year;
	}


}
