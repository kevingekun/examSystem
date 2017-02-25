package com.wondersgroup.falcon.beans.common;

/**
 * <p>Title: Wonders CC</p>
 * <p>Description: ��﹫˾�ͻ�����ϵͳ</p>
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

	// ��Dateת��ΪString, ��ʽΪ"yyyy-MM-dd HH:mm:ss"
	public static String convertDT(Date dt) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdt = SDF.format(dt);
		return strdt;
	}

	// ��Stringת��ΪDate, Ҫ��String��ʽΪ"yyyy-MM-dd HH:mm:ss"
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

	// �õ���������, ��ʽΪ"yyyy-MM-dd"
	public static String getTodayStr() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		String strdt = SDF.format(new java.util.Date());
		return strdt;
	}

	// �õ�����ʱ��, ��ʽΪ"yyyyMMddhhmmss"
	public static String getNowStr() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
		String strdt = SDF.format(new java.util.Date());
		return strdt;
	}

	// ���ܺ���, ��ʱ��ʵ��
	public static String encrypt_pwd(String pwd) {
		return pwd;
	}

	// �õ�min���Ӻ��ʱ��
	public static String getDTAfterMin(Date dt, int min) {
		Date newdt = dt;

		newdt.setTime(newdt.getTime() + min * 60000);
		return convertDT(newdt);
	}

	// ��ʱû��ʹ��, Comment by Sjm
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

	// ������Ĳ�ѯ����
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

	// ��������ת������������JSP��
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

	// ����Notes��Ϣ��ӣ�����Ϣǰ����agent_id
	public static String appendstr(String str, String agent_id) {
		String result = str.trim();
		if (result.equals(""))
			return result;
		else {
			return agent_id + ":\r\n" + result + "\r\n";
		}
	}

	// ���ع��ϼ������������
	public static String getcaseleveldesc(String case_level) {

		String caseleveldesc = "";

		switch (Integer.valueOf(case_level).intValue()) {
		case 1:
			caseleveldesc = "һ������";
			break;
		case 2:
			caseleveldesc = "��������";
			break;
		case 3:
			caseleveldesc = "��������";
			break;
		case 4:
			caseleveldesc = "�ļ�����";
			break;
		}

		return caseleveldesc;
	}

	// �ж�����
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
		if (Status.equals(new Byte ((byte)2))) Statusname = "../images/cs.gif";	//��ʼ״̬
		if (Status.equals(new Byte ((byte)3))) Statusname = "../images/kx.gif"; //����
		if (Status.equals(new Byte ((byte)4))) Statusname = "../images/bx.gif";	//��ϯ
		if (Status.equals(new Byte ((byte)5))) Statusname = "../images/zl.gif";	//��������
		if (Status.equals(new Byte ((byte)6))) Statusname = "../images/jt.gif";	//��������
		if (Status.equals(new Byte ((byte)7))) Statusname = "../images/jt.gif";	//����Ҷ�
		if (Status.equals(new Byte ((byte)8))) Statusname = "../images/hf.gif";	//��������
		if (Status.equals(new Byte ((byte)9))) Statusname = "../images/hf.gif";		//������
		if (Status.equals(new Byte ((byte)10))) Statusname = "../images/hf.gif";	//��������
		if (Status.equals(new Byte ((byte)11))) Statusname = "../images/hf.gif";	//������
		if (Status.equals(new Byte ((byte)12))) Statusname = "../images/zj.gif";	//����ת��
		if (Status.equals(new Byte ((byte)13))) Statusname = "../images/xs.gif";	//Э����
		if (Status.equals(new Byte ((byte)14))) Statusname = "../images/zj.gif";	//ת������
		if (Status.equals(new Byte ((byte)15))) Statusname = "../images/xs.gif";	//Э����
		if (Status.equals(new Byte ((byte)16))) Statusname = "../images/hy.gif";	//��������
		if (Status.equals(new Byte ((byte)17))) Statusname = "../images/hy.gif";	//��������
		if (Status.equals(new Byte ((byte)18))) Statusname = "../images/hy.gif";	//��������
		if (Status.equals(new Byte ((byte)19))) Statusname = "../images/hy.gif";	//��������
		if (Status.equals(new Byte ((byte)20))) Statusname = "../images/qc.gif";	//��ʼǿ��
		if (Status.equals(new Byte ((byte)21))) Statusname = "../images/jjt.gif";	//��ʼ����
		if (Status.equals(new Byte ((byte)22))) Statusname = "../images/qc.gif";	//ǿ����
		if (Status.equals(new Byte ((byte)23))) Statusname = "../images/jjt.gif";	//������
		if (Status.equals(new Byte ((byte)24))) Statusname = "../images/wb.gif";	//��ʼ�Ⲧ
		if (Status.equals(new Byte ((byte)25))) Statusname = "../images/wb.gif";	//�Ⲧͨ��
		if (Status.equals(new Byte ((byte)26))) Statusname = "../images/zj.gif";	//Զ��ת��
		if (Status.equals(new Byte ((byte)27))) Statusname = "../images/xs.gif";	//Զ��Э��
		if (Status.equals(new Byte ((byte)28))) Statusname = "../images/hy.gif";	//Զ�̻���
		if (Status.equals(new Byte ((byte)29))) Statusname = "../images/hy.gif";	//Զ�̻���
		if (Status.equals(new Byte ((byte)30))) Statusname = "../images/jt.gif";	//����¼��
		if (Status.equals(new Byte ((byte)31))) Statusname = "../images/hy.gif";	//���鲦��
		if (Status.equals(new Byte ((byte)32))) Statusname = "../images/hy.gif";	//�෽����
		if (Status.equals(new Byte ((byte)33))) Statusname = "../images/zl.gif";	//��������
		if (Status.equals(new Byte ((byte)34))) Statusname = "../images/hy.gif";	//�෽����
		return Statusname;
	}
}