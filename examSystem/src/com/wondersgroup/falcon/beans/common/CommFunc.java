package com.wondersgroup.falcon.beans.common;

/**
 * <p>Title: Wonders CC</p>
 * <p>Description: 万达公司客户服务系统</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Wonders Info. </p>
 * @author not attributable
 * @version 1.0
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger; 

public class CommFunc {

	static Logger logger = Logger.getLogger(CommFunc.class);

	// ***********************************************//
	public static Date strToDate(String strdt) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		if (strdt ==null | strdt.equals(""))
			return dt;
		try {
			dt = SDF.parse(strdt);
		} catch (Exception e) {
			logger.error("Can't convert '" + strdt + "' to Date: "
					+ e.getMessage());
		}
		return dt;
	}

	public static String dateToStr(Date dt) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		String strdt = SDF.format(dt);
		return strdt;
	}
	// ***********************************************//

	// 将Date转化为String, 格式为"yyyy-MM-dd HH:mm:ss"
	public static String convertDT(Date dt) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdt = SDF.format(dt);
		return strdt;
	}

	// 将String转化为Date, 要求String格式为"yyyy-MM-dd HH:mm:ss"
	public static Date convertString(String strdt) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = SDF.parse(strdt);
		} catch (Exception e) {
			logger.error("Can't convert '" + strdt + "' to Date: "
					+ e.getMessage());
		}
		return dt;
	}

	// 得到今天日期, 格式为"yyyy-MM-dd"
	public static String getTodayStr() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		String strdt = SDF.format(new java.util.Date());
		return strdt;
	}

	// 得到今天时间, 格式为"yyyyMMddhhmmss"
	public static String getNowStr() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
		String strdt = SDF.format(new java.util.Date());
		return strdt;
	}

	// 加密函数, 暂时不实现
	public static String encrypt_pwd(String pwd) {
		return pwd;
	}

	// 得到min分钟后的时间
	public static String getDTAfterMin(Date dt, int min) {
		Date newdt = dt;

		newdt.setTime(newdt.getTime() + min * 60000);
		return convertDT(newdt);
	}

	// 暂时没有使用, Comment by Sjm
	// // change long to string
	// // String Format: 00001234, 8 bits
	// public static String LongToStr(long value) {
	// String result = String.valueOf(value);
	// String zero8 = "00000000";
	// int l = result.length();
	//
	// if (l < 8)
	// result = zero8.substring(0, 8 - l) + result;
	//
	// return result;
	// }

	// 解决中文查询问题
	public static String chgStr(String str) {

		if (str == null)
			return null;

		try {
			byte[] temp_t = str.getBytes("ISO-8859-1");
			return new String(temp_t);
		} catch (Exception e) {
			logger.error("Can't chgStr '" + str + "': " + e.getMessage());
			return null;
		}
	}

	// 以下两个转换函数，用于JSP中
	public static String preproc(String str) {
		String result;

		if (str == null)
			return null;
		result = str.replaceAll("'", "''");
		result = result.replaceAll("\\\\\r\n", "\\\\ \r\n");

		return result;
	}

	public static String postproc(String str) {
		String result;

		if (str == null)
			return null;
		result = str.replaceAll("\\\\", "\\\\\\\\");
		result = result.replaceAll("\r\n", "\\\\r\\\\n");
		result = result.replaceAll("\"", "\\\\\"");

		return result;
	}

	// 用于Notes信息添加，在信息前增加agent_id
	public static String appendstr(String str, String agent_id) {
		String result = str.trim();
		if (result.equals(""))
			return result;
		else {
			return agent_id + ":\r\n" + result + "\r\n";
		}
	}

	// 返回故障级别的中文描述
	public static String getcaseleveldesc(String case_level) {

		String caseleveldesc = "";

		switch (Integer.valueOf(case_level).intValue()) {
		case 1:
			caseleveldesc = "一级故障";
			break;
		case 2:
			caseleveldesc = "二级故障";
			break;
		case 3:
			caseleveldesc = "三级故障";
			break;
		case 4:
			caseleveldesc = "四级故障";
			break;
		}

		return caseleveldesc;
	}

	// 判断闰年
	public static boolean isleapyear(String year) {

		if (year == null)
			return false;

		int y = Integer.valueOf(year).intValue();

		if (y % 4 == 0) {
			if (y % 100 == 0) {
				if (y % 400 == 0)
					return true;
				return false;
			} else
				return true;
		} else
			return false;
	}
	
	public static String getStatus(Byte Status){
		String Statusname = "../images/wdl.gif";
		if (Status.equals(new Byte ((byte)1))) Statusname = "../images/wdl.gif";
		if (Status.equals(new Byte ((byte)2))) Statusname = "../images/cs.gif";	//初始状态
		if (Status.equals(new Byte ((byte)3))) Statusname = "../images/kx.gif"; //空闲
		if (Status.equals(new Byte ((byte)4))) Statusname = "../images/bx.gif";	//闭席
		if (Status.equals(new Byte ((byte)5))) Statusname = "../images/zl.gif";	//市民来电
		if (Status.equals(new Byte ((byte)6))) Statusname = "../images/jt.gif";	//接听来电
		if (Status.equals(new Byte ((byte)7))) Statusname = "../images/jt.gif";	//来电挂断
		if (Status.equals(new Byte ((byte)8))) Statusname = "../images/hf.gif";	//互访来电
		if (Status.equals(new Byte ((byte)9))) Statusname = "../images/hf.gif";		//互访中
		if (Status.equals(new Byte ((byte)10))) Statusname = "../images/hf.gif";	//主动互访
		if (Status.equals(new Byte ((byte)11))) Statusname = "../images/hf.gif";	//互访中
		if (Status.equals(new Byte ((byte)12))) Statusname = "../images/zj.gif";	//申请转接
		if (Status.equals(new Byte ((byte)13))) Statusname = "../images/xs.gif";	//协商中
		if (Status.equals(new Byte ((byte)14))) Statusname = "../images/zj.gif";	//转接来电
		if (Status.equals(new Byte ((byte)15))) Statusname = "../images/xs.gif";	//协商中
		if (Status.equals(new Byte ((byte)16))) Statusname = "../images/hy.gif";	//三方会议
		if (Status.equals(new Byte ((byte)17))) Statusname = "../images/hy.gif";	//三方会议
		if (Status.equals(new Byte ((byte)18))) Statusname = "../images/hy.gif";	//三方会议
		if (Status.equals(new Byte ((byte)19))) Statusname = "../images/hy.gif";	//三方会议
		if (Status.equals(new Byte ((byte)20))) Statusname = "../images/qc.gif";	//开始强插
		if (Status.equals(new Byte ((byte)21))) Statusname = "../images/jjt.gif";	//开始监听
		if (Status.equals(new Byte ((byte)22))) Statusname = "../images/qc.gif";	//强插中
		if (Status.equals(new Byte ((byte)23))) Statusname = "../images/jjt.gif";	//监听中
		if (Status.equals(new Byte ((byte)24))) Statusname = "../images/wb.gif";	//开始外拨
		if (Status.equals(new Byte ((byte)25))) Statusname = "../images/wb.gif";	//外拨通话
		if (Status.equals(new Byte ((byte)26))) Statusname = "../images/zj.gif";	//远程转接
		if (Status.equals(new Byte ((byte)27))) Statusname = "../images/xs.gif";	//远程协商
		if (Status.equals(new Byte ((byte)28))) Statusname = "../images/hy.gif";	//远程会议
		if (Status.equals(new Byte ((byte)29))) Statusname = "../images/hy.gif";	//远程会议
		if (Status.equals(new Byte ((byte)30))) Statusname = "../images/jt.gif";	//播放录音
		if (Status.equals(new Byte ((byte)31))) Statusname = "../images/hy.gif";	//会议拨号
		if (Status.equals(new Byte ((byte)32))) Statusname = "../images/hy.gif";	//多方会议
		if (Status.equals(new Byte ((byte)33))) Statusname = "../images/zl.gif";	//会议振铃
		if (Status.equals(new Byte ((byte)34))) Statusname = "../images/hy.gif";	//多方会议
		return Statusname;
	}
}