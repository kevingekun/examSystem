package com.wondersgroup.falcon.Util;

public class ProcedureUrl {

	public static final String PROC_URL_STRING;//存过调用地址
	
	public static final String E_PAPER_TEAM = "qdyth.e_paper_team@QDYTH";
	public static final String E_PAPERS = "qdyth.e_papers@QDYTH";
	public static final String E_USER_AUTHORITIES = "qdyth.E_USER_AUTHORITIES_YTH@QDYTH";
	
	public static final String WS_URL = "http://localhost:8080/sx_wcms_front/services/dispatchService?wsdl";
	public static final String WS_URL_YTH;//webservice与一体化交互地址
	public static final String WS_URL_JDZX;//鉴定所与鉴定中心交互地址
	
	public static final String IP_LIMIT;//限制ip
	static{
		PropertiesUtil pu =new PropertiesUtil("/database.properties");
		PROC_URL_STRING = pu.getProperties("PROC_URL_STRING");
		WS_URL_YTH = pu.getProperties("WS_URL_YTH");
		WS_URL_JDZX = pu.getProperties("WS_URL_JDZX");
		IP_LIMIT = pu.getProperties("IP_LIMIT");
	}
}
