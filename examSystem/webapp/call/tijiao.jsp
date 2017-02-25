<%@ page contentType="text/html;charset=gb2312"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>无标题文档</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
<%@ page import="java.util.*"%>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil"%>
<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
<%@ page import="com.wondersgroup.falcon.model.shortmessage.*" %>
<%@ page import="java.util.List,java.util.Date,java.text.SimpleDateFormat,java.util.Set,java.util.HashSet" %>

<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
<%@ page import="com.wondersgroup.falcon.dao.auth.*" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.beans.common.*" %>
<%@ page import="com.wondersgroup.falcon.beans.auth.AuthBeans"%>
<%@ page import="com.wondersgroup.falcon.model.archives.*" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
	<body>
		<%
		String gonghao=((UserDetailsImpl)util.getUserDetails()).getUser().getUsername().toString();
	   
	    Users users = new Users();
	    Users sender = new Users();	
	    AuthBeans zd = new AuthBeans();	
        sender=zd.findByUsername(gonghao);
       
       
                Users zhijian1 = zd.findByUsername("032");
				Users zhijian2 = zd.findByUsername("036");;
				Users zhijian3 = zd.findByUsername("051");
				Users zhijian4 = zd.findByUsername("031");
				
				Set temp=new HashSet();
				temp.add(zhijian1);
				temp.add(zhijian2);
				temp.add(zhijian3);
				temp.add(zhijian4);
	    String callid = request.getParameter("callid");
		ZhijianDAO pit = new ZhijianDAO();   
		String ids = request.getParameter("checkboxids");
			if(ids.length()>0){
			StringTokenizer stk = new StringTokenizer(ids,";");
			int count1 = stk.countTokens();
			for(int i=0;i<count1;i++){
		 pit.replace2(stk.nextToken());
		}}
			out.println("<script>");
			out.println("alert('提交成功');");
			out.println("window.location = 'main_right.jsp?'");
			out.println("</script>");	
		%>
	</body>
</html>
