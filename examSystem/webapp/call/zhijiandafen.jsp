<%@page contentType = "text/html;charset=gbk" %>
<%@page import="java.util.List,java.util.Iterator,java.util.ArrayList,java.util.Set,java.util.Date,java.text.SimpleDateFormat" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="com.wondersgroup.falcon.model.citizeninfo.History" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*" %>
<%@ page import="com.wondersgroup.falcon.beans.citizeninfo.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Set,java.util.HashSet"%>
<%@ page import="com.wondersgroup.falcon.persistence.DBConnection" %>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<%@ page import="java.text.DecimalFormat" %>


<html>
<head>
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
</head>
  
  <body>
 <%
 String a = request.getParameter("a");
              if(a==null) a="";
              
             
 DecimalFormat per = new DecimalFormat("0.00");
 	double fenshu = 0;
 	double zongfen = 0;
 	double value = 0;
 	double fenshu1 = 0;
 	double zongfen1 = 0;
	double fenshu2 = 0;
 	double zongfen2 = 0;
 	String mark = "";
 	String gonghao = request.getParameter("gonghao");
 	String auth = request.getParameter("auth");
 	String callid = request.getParameter("callid");
 	String dafenmemo = request.getParameter("dafenmemo");
 	String startrecordtime1 = request.getParameter("startrecordtime");
 	System.out.print("time1234444444444444================>"+startrecordtime1);
 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	Date startrecordtime =formatter.parse(startrecordtime1);
 	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String formatime = sdf.format(date);
	Zhijiankaohe zhijiankaohe = new Zhijiankaohe();
	
	Zhijianpingfen pingfen = new Zhijianpingfen();
	ZhijiankaoheDAO service = new ZhijiankaoheDAO();
	List zhijiankaohelist;
	zhijiankaohelist = service.findAll();
	
			if(a.equals("1")){
				session.removeAttribute(callid);
			}
	
	//String strSql= "delete zhijianpingfenmingxi where pingfenid='"+callid+"'";
	//DBConnection db = new DBConnection();
	//db.execUpdate(strSql);
	//db.close();
	for(int i=0;i<zhijiankaohelist.size();i++){
		Mingxi mingxi = new Mingxi();
		zhijiankaohe = (Zhijiankaohe)zhijiankaohelist.get(i);
		value = Double.parseDouble(zhijiankaohe.getValue());
		mark = request.getParameter("radio"+zhijiankaohe.getId().toString());
		System.out.println("mark=======>"+mark);
		if(mark!=null&&!mark.equals("")){
			fenshu = Double.parseDouble(mark);
			fenshu = Double.parseDouble(per.format((fenshu/4)*(value/100)*100));
			zongfen +=fenshu;
		}
		mingxi = service.findMingxienBykaoId(zhijiankaohe.getId().toString(),callid);
		if(mingxi==null) mingxi = new Mingxi();
		//if(mark!=null&&!mark.equals("")&&zhijiankaohe.getParentid().equals("50")){
		//	fenshu = Double.parseDouble(mark);
		//	fenshu = (fenshu/4)*(value/100)*100*0.05;
	//		zongfen +=fenshu;
	//	}
		//fenshu1 = ((double)Math.floor(fenshu*1000+.55))/1000;
		
		//fenshu2 = ((double)Math.floor(fenshu1*100+.55))/100;
		mingxi.setKaoheid(zhijiankaohe.getId().toString());
		mingxi.setPingfenid(callid);
		mingxi.setMark(new Double(fenshu).toString());
		mingxi.setRiqi(date);
		service.addZhijianpingfenmingxi(mingxi);
	}
	//zongfen1 = ((double)Math.floor(zongfen*1000+.55))/1000;
	//zongfen2 = ((double)Math.floor(zongfen1*100+.55))/100;
	pingfen = service.findBypingfenid(callid);
	if(pingfen==null) pingfen = new Zhijianpingfen();
	pingfen.setMark(new Double(zongfen).toString());
	pingfen.setPingfenid(callid);
	pingfen.setAuth(auth);
	pingfen.setGonghao(gonghao);
	pingfen.setRiqi(date);
	pingfen.setStartrecordtime(startrecordtime);
	pingfen.setDafenmemo(dafenmemo);
	pingfen.setFlag("9");
	service.addZhijianpingfen(pingfen);
	 ZhijianDAO pit = new ZhijianDAO();   
	  pit.replace(callid);
	
			if(a.equals("1")){
			out.println("<script>");
			out.println("alert('操作成功');");
			out.println("window.location = '../zhijian/result.jsp'");
			out.println("</script>");
			}
			else{
			out.println("<script>");
			out.println("alert('操作成功');");
			out.println("window.location = 'main_right.jsp'");
			out.println("</script>");
			}
			
	
  %>
  </body>
</html>
