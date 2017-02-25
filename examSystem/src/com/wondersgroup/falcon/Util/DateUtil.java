package com.wondersgroup.falcon.Util;

import java.text.*;
import java.util.*;

/**
 * <p>Title: 应用于日期的处理 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Wonders Information CO.,LTD.</p>
 * @author 万紫红
 * @version 1.0
 */

public class DateUtil {
	private static Calendar cal = new GregorianCalendar();
	public static final String FULLDATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SMALLDATEFORMAT = "yyyy-MM-dd";

	public static int getCurrentEra() {
		return cal.get(Calendar.ERA);
	}

	public static int getCurrentYear() {
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return cal.get(Calendar.MONTH);
	}

	public static int getCurrentDay() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getCurrentDayOfWeek() {
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String DateToStr(Date date, String format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(date).toString();
		}
		catch (Exception e) {
			SimpleDateFormat df = new SimpleDateFormat();
			return df.format(date).toString();
		}
	}

	public static String DateToStr(Date date) {
		SimpleDateFormat df = new SimpleDateFormat();
		return df.format(date).toString();
	}

	/**  把字符型转化为日期型
	 *
	 * @param str 2005/3/11
	 * @return
	 */
	public static Date StrToDate(String str_date1) {
		String s = "0000-00-00";
		if (str_date1 == null || "".equals(str_date1)) {
			return null;
		}
		else {
			s = str_date1.trim();
		}
		String sYear = s.substring(0, s.indexOf('-'));
		String sMonth = s.substring(s.indexOf('-') + 1,
				s.lastIndexOf('-'));
		String sDay = s.substring(s.lastIndexOf('-') + 1);
		int year = Integer.parseInt(sYear);
		int month = Integer.parseInt(sMonth);
		int day = Integer.parseInt(sDay);
		GregorianCalendar dateline = new GregorianCalendar(year, month - 1, day);
		return dateline.getTime();

	}

	//获得now时间
	public static String getCurrentDateTime(String format) {
		Date date = new Date();
		return DateToStr(date, format);
	}

	public static String getCurrentDateTime() {
		Date date = new Date();
		return DateToStr(date);
	}

	//op="+" or "-"
	public static String CaculateDateTime(String date, String format,
			int year, int month, int day,
			int hour, int minute, int second) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date dt = df.parse(date);
			Calendar c = new GregorianCalendar();
			c.setTime(dt);

			c.add(GregorianCalendar.YEAR, year);
			c.add(GregorianCalendar.MONTH, month);
			c.add(GregorianCalendar.DAY_OF_MONTH, day);
			c.add(GregorianCalendar.HOUR_OF_DAY, hour);
			c.add(GregorianCalendar.MINUTE, minute);
			c.add(GregorianCalendar.SECOND, second);

			return df.format(c.getTime()).toString();
		}
		catch (Exception e) {
			return date;
		}
	}

	//0  date1=date2
	//>0 date1>date2
	//<0 date1<date2
	public static int CompareDate(String date1, String date2, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			return dt1.compareTo(dt2);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public static Date formatDate(String strDate, String format) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			
			if(strDate==null || strDate.equals("")){
				
				return null;
			}
			date = formatter.parse(strDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/** 以字符串形式显示日期
	 *
	 * @param date
	 * @return
	 */
	/*public static String DateToStr(Date date) {
    int yy = date.getYear() + 1900;
    int mm = date.getMonth() + 1;
    int dd = date.getDate();

    StringBuffer sb = new StringBuffer();
    if (mm < 10) {
      sb.append(yy).append("/").append("0").append(mm).append("/");
    }
    else {
      sb.append(yy).append("/").append(mm).append("/");

    }
    if (dd < 10) {
      sb.append("0").append(dd);
    }
    else {
      sb.append(dd);

    }
    return sb.toString();

     }*/

	/** 以字符串形式显示日期
	 *
	 * @param date
	 * @param format 显示日期的格式（yy/mm/dd 或者 yy/mm）
	 * @return
	 */
	/*public static String DateToStr(Date date, String format) {
    int yy = date.getYear() + 1900;
    int mm = date.getMonth() + 1;
    int dd = date.getDate();

    if (yy == 1900) {
      return "";
    }

    StringBuffer str_yy = new StringBuffer();
    StringBuffer str_mm = new StringBuffer();
    StringBuffer str_dd = new StringBuffer();

    str_yy.append(yy);

    if (mm < 10) {
      str_mm.append("0").append(mm);
    }
    else {
      str_mm.append(mm);

    }
    if (dd < 10) {
      str_dd.append("0").append(dd);
    }
    else {
      str_dd.append(dd);

    }
    StringBuffer sb = new StringBuffer();

    if (format != null) {
      if (format.compareTo("yy/mm") == 0) {
    sb.append(str_yy.toString()).append("/").append(str_mm.toString());
      }
      return sb.toString();
    }
    sb.append(str_yy.toString()).append("/").append(str_mm.toString()).append(
    "/").append(str_dd.toString());

    return sb.toString();
     }*/
	public static long nowDays(Date orient){
		Date now = new Date();
		long tonow=	now.getTime() - orient.getTime();
		return tonow/86400000;
	}

	public static void main(String[] args) {
		DateUtil.formatDate("2009-09-09 8:00:00", DateUtil.FULLDATEFORMAT);
		//DateUtil.StrToDate("2009-09-09 8:00");
		
	}

}

